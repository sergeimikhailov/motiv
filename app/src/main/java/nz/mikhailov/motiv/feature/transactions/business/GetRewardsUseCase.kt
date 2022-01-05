package nz.mikhailov.motiv.feature.transactions.business

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nz.mikhailov.motiv.Features
import nz.mikhailov.motiv.feature.rewards.RewardsFeature

class GetRewardsUseCase(
    private val rewardsFeature: RewardsFeature = Features.rewards,
) {

    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        rewardsFeature.getRewards()
    }
}
