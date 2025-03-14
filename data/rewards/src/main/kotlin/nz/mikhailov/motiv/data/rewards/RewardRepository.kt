package nz.mikhailov.motiv.data.rewards

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RewardRepository @Inject constructor(
    private val localRewardDataStore: LocalRewardDataStore,
) {
    fun getRewards(): Flow<List<Reward>> = localRewardDataStore.read().map { rewardRecords ->
        rewardRecords.map(::mapToReward)
    }

    private fun mapToReward(record: RewardRecord): Reward = when (record.name.lowercase()) {
        "code" -> Reward.Code(record.cost)
        "exercise" -> Reward.Exercise(record.cost)
        "study" -> Reward.Study(record.cost)
        "no junk food" -> Reward.NoJunkFood(record.cost)
        else -> throw IllegalArgumentException("Unknown reward type: ${record.name}")
    }
}