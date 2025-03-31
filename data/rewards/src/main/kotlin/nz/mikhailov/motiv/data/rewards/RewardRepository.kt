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

    suspend fun create(reward: Reward): Reward? {
        val record = mapToRewardRecord(reward)
        val created = dataStore.create(record)
        return mapToReward(created)
    }

    suspend fun update(reward: Reward): Reward? {
        val record = mapToRewardRecord(reward)
        val updated = dataStore.update(record)
        return mapToReward(updated)
    }

    suspend fun delete(id: String) {
        dataStore.delete(id)
    }

    private fun mapToReward(record: RewardRecord): Reward? {
        return Reward(
            id = record.id,
            name = record.name,
            amount = record.cost / 100.0,
            icon = RewardIcon.fromString(record.icon) ?: return null
        )
    }
    
    private fun mapToRewardRecord(reward: Reward): RewardRecord {
        return RewardRecord(
            id = reward.id,
            name = reward.name,
            cost = (reward.amount * 100).toInt(),
            icon = reward.icon.toString()
        )
    }
}
