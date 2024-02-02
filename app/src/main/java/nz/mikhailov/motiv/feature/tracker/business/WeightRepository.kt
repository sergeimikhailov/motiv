package nz.mikhailov.motiv.feature.tracker.business

import kotlinx.coroutines.flow.map
import nz.mikhailov.motiv.feature.tracker.data.LocalWeightRecordDataStore
import nz.mikhailov.motiv.feature.tracker.data.WeightRecord
import java.time.Instant
import javax.inject.Inject

class WeightRepository @Inject constructor(
    private val dataStore: LocalWeightRecordDataStore,
) {
    fun latestWeights(limit: Int = 100) = dataStore.read(limit).map { it.toBo() }

    suspend fun recordWeight(weight: Double) = dataStore.insert(WeightRecord(
        date = Instant.now(),
        weight = (weight * 10).toInt(),
    ))
}

private fun List<WeightRecord>.toBo() = map(WeightRecord::toBo)

private fun WeightRecord.toBo() = Weight(
    date = date,
    weight = weight / 10.0,
)