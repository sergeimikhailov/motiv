package nz.mikhailov.motiv.feature.rewards

import kotlinx.coroutines.flow.Flow
import nz.mikhailov.motiv.feature.rewards.business.GetRewardsUseCase
import nz.mikhailov.motiv.feature.rewards.business.model.Reward

interface RewardsFeature {
    fun getRewards(): Flow<List<Reward>>
}

class RewardsFacade private constructor(
    private val getRewardsUseCase: GetRewardsUseCase = GetRewardsUseCase(),
) : RewardsFeature {

    companion object {
        fun newInstance() = RewardsFacade()
    }

    override fun getRewards(): Flow<List<Reward>> = getRewardsUseCase()
}
