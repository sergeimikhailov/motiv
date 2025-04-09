package nz.mikhailov.motiv.feature.settings

import kotlinx.coroutines.flow.Flow
import nz.mikhailov.motiv.data.rewards.Reward
import nz.mikhailov.motiv.feature.settings.business.CreateRewardUseCase
import nz.mikhailov.motiv.feature.settings.business.GetRewardsUseCase
import nz.mikhailov.motiv.feature.settings.business.UpdateRewardUseCase
import nz.mikhailov.motiv.feature.settings.ui.model.RewardUIO
import javax.inject.Inject

class SettingsFeature @Inject constructor(
    private val getRewardsUseCase: GetRewardsUseCase,
    private val createRewardUseCase: CreateRewardUseCase,
    private val updateRewardUseCase: UpdateRewardUseCase,
){
    fun getRewards(): Flow<List<Reward>> = getRewardsUseCase()
    suspend fun createReward(reward: RewardUIO) = createRewardUseCase(reward)
    suspend fun updateReward(reward: RewardUIO) = updateRewardUseCase(reward)
}
