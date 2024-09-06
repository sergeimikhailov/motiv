package nz.mikhailov.motiv.data.weight

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalWeightRecordDataStore {

    @Query("SELECT * FROM weight ORDER BY date DESC LIMIT :limit")
    fun read(limit: Int): Flow<List<WeightRecord>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(record: WeightRecord)
}
