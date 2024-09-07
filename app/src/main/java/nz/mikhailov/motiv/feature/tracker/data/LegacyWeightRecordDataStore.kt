package nz.mikhailov.motiv.feature.tracker.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LegacyWeightRecordDataStore {

    @Query("SELECT * FROM weight ORDER BY date DESC")
    fun read(): Flow<List<WeightRecord>>
}
