package nz.mikhailov.motiv.feature.settings

import kotlinx.coroutines.flow.Flow
import nz.mikhailov.motiv.feature.settings.business.GetRewardsUseCase
import nz.mikhailov.motiv.feature.rewards.business.model.Reward

interface SettingsFeature {
    suspend fun getRewards(): Flow<List<Reward>>
}

class SettingsFacade private constructor(
    private val getRewardsUseCase: GetRewardsUseCase = GetRewardsUseCase(),
): SettingsFeature {

    companion object {
        fun newInstance() = SettingsFacade()
    }

    override suspend fun getRewards() = getRewardsUseCase()
}
