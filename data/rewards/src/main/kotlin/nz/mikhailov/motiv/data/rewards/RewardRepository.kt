package nz.mikhailov.motiv.data.rewards

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RewardRepository @Inject constructor(
    dataStore: LocalRewardDataStore,
) {
    val rewards: Flow<List<Reward>> = dataStore.rewards.map { rewardRecords ->
        rewardRecords.mapNotNull(::mapToReward)
    }

    private fun mapToReward(record: RewardRecord): Reward? {
        return Reward(
            id = record.id,
            name = record.name,
            amount = record.cost / 100.0,
            icon = RewardIcon.fromString(record.icon) ?: return null
        )
    }
}
