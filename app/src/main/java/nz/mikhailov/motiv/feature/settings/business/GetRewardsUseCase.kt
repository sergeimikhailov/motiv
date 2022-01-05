package nz.mikhailov.motiv.feature.settings.business

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import nz.mikhailov.motiv.Features
import nz.mikhailov.motiv.feature.rewards.RewardsFeature
import nz.mikhailov.motiv.feature.rewards.business.model.Reward

class GetRewardsUseCase(
    private val rewardsFeature: RewardsFeature = Features.rewards,
) {

    suspend operator fun invoke(): Flow<List<Reward>> = withContext(Dispatchers.IO) {
        rewardsFeature.getRewards()
    }
}
