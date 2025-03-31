package nz.mikhailov.motiv.data.rewards

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalRewardDataStore {

    @get:Query("SELECT * FROM rewards")
    val rewards: Flow<List<RewardRecord>>
}
