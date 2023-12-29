package nz.mikhailov.motiv.feature.rewards

import kotlinx.coroutines.flow.Flow
import nz.mikhailov.motiv.feature.rewards.business.GetRewardsUseCase
import nz.mikhailov.motiv.feature.rewards.business.model.Reward
import javax.inject.Inject

class RewardsFeature @Inject constructor(
    private val getRewardsUseCase: GetRewardsUseCase,
) {
    fun getRewards(): Flow<List<Reward>> = getRewardsUseCase()
}
