package nz.mikhailov.motiv.feature.settings

import kotlinx.coroutines.flow.Flow
import nz.mikhailov.motiv.feature.rewards.business.model.Reward
import nz.mikhailov.motiv.feature.settings.business.GetRewardsUseCase
import javax.inject.Inject

class SettingsFeature @Inject constructor(
    private val getRewardsUseCase: GetRewardsUseCase,
){
    fun getRewards(): Flow<List<Reward>> = getRewardsUseCase()
}
