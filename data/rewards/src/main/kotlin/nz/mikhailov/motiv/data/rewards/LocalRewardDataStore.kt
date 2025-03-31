package nz.mikhailov.motiv.data.rewards

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalRewardDataStore {

    @get:Query("SELECT * FROM rewards")
    val rewards: Flow<List<RewardRecord>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(reward: RewardRecord): RewardRecord
    
    @Update
    suspend fun update(reward: RewardRecord): RewardRecord
    
    @Query("DELETE FROM rewards WHERE id = :id")
    suspend fun delete(id: String)
}
