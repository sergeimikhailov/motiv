package nz.mikhailov.motiv.feature.transactions.business

import nz.mikhailov.motiv.Features
import nz.mikhailov.motiv.feature.rewards.RewardsFeature

class GetRewardsUseCase(
    private val rewardsFeature: RewardsFeature = Features.rewards,
) {

    suspend operator fun invoke() = rewardsFeature.getRewards()
}
