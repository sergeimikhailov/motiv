package nz.mikhailov.motiv.data.rewards

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RewardRepository @Inject constructor(
    private val dataStore: LocalRewardDataStore,
) {
    val rewards: Flow<List<Reward>> = dataStore.rewards.map { rewardRecords ->
        rewardRecords.mapNotNull(::mapToReward)
    }

    suspend fun create(reward: Reward) = dataStore.create(reward.toRecord())

    suspend fun update(reward: Reward) = dataStore.update(reward.toRecord())

    suspend fun delete(id: String) = dataStore.delete(id)

    private fun mapToReward(record: RewardRecord): Reward? {
        return Reward(
            id = record.id,
            name = record.name,
            amount = record.cost / 100.0,
            icon = RewardIcon.fromString(record.icon) ?: return null
        )
    }
    
    private fun Reward.toRecord() = RewardRecord(
        id = id,
        name = name,
        cost = (amount * 100).toInt(),
        icon = icon.toString()
    )
}
