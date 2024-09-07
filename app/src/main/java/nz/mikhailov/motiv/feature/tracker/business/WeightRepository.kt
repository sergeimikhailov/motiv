package nz.mikhailov.motiv.feature.tracker.business

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import nz.mikhailov.motiv.data.weight.LocalWeightRecordDataStore
import nz.mikhailov.motiv.data.weight.WeightRecord
import nz.mikhailov.motiv.feature.tracker.data.LegacyWeightRecordDataStore
import java.time.Instant
import javax.inject.Inject

class WeightRepository @Inject constructor(
    private val dataStore: LocalWeightRecordDataStore,
    private val legacyDataStore: LegacyWeightRecordDataStore,
) {
    fun latestWeights(limit: Int = 100) = dataStore.read(limit).map { it.toBo() }

    suspend fun migrateData() {
        // only migrating if new store is empty
        if (dataStore.read(1).first().isNotEmpty()) return

        legacyDataStore.read().firstOrNull()?.forEach {
            dataStore.insert(WeightRecord(
                date = it.date,
                weight = it.weight,
            ))
        }
    }

    suspend fun recordWeight(weight: Double?) {
        if (weight == null || weight <= 0) return
        dataStore.insert(WeightRecord(
            date = Instant.now(),
            weight = (weight * 10).toInt(),
        ))
    }
}

private fun List<WeightRecord>.toBo() = map(WeightRecord::toBo)

private fun WeightRecord.toBo() = Weight(
    date = date,
    weight = weight / 10.0,
)