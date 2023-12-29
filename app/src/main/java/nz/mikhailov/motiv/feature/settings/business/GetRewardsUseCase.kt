package nz.mikhailov.motiv.feature.settings.business

import kotlinx.coroutines.flow.Flow
import nz.mikhailov.motiv.feature.rewards.RewardsFeature
import nz.mikhailov.motiv.feature.rewards.business.model.Reward
import javax.inject.Inject

class GetRewardsUseCase @Inject constructor(
    private val rewardsFeature: RewardsFeature,
) {

    operator fun invoke(): Flow<List<Reward>> =
        rewardsFeature.getRewards()
}
