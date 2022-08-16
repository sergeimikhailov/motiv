package nz.mikhailov.motiv.feature.settings.business

import kotlinx.coroutines.flow.Flow
import nz.mikhailov.motiv.Features
import nz.mikhailov.motiv.feature.rewards.RewardsFeature
import nz.mikhailov.motiv.feature.rewards.business.model.Reward

class GetRewardsUseCase(
    private val rewardsFeature: RewardsFeature = Features.rewards,
) {

    operator fun invoke(): Flow<List<Reward>> =
        rewardsFeature.getRewards()
}
