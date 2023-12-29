package nz.mikhailov.motiv.feature.transactions.business

import nz.mikhailov.motiv.feature.rewards.RewardsFeature
import javax.inject.Inject

class GetRewardsUseCase @Inject constructor(
    private val rewardsFeature: RewardsFeature,
) {

    operator fun invoke() =
        rewardsFeature.getRewards()
}
