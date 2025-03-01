package nz.mikhailov.motiv.data.rewards

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalRewardDataStore {

    @Query("SELECT * FROM rewards")
    fun read(): Flow<List<RewardRecord>>
}
