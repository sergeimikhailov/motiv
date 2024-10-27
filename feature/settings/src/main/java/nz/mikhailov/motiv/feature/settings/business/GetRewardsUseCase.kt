package nz.mikhailov.motiv.feature.settings.business

import kotlinx.coroutines.flow.Flow
import nz.mikhailov.motiv.data.rewards.HardcodedRewardDataSource
import nz.mikhailov.motiv.data.rewards.Reward
import javax.inject.Inject

class GetRewardsUseCase @Inject constructor(
    private val dataSource: HardcodedRewardDataSource,
) {

    operator fun invoke(): Flow<List<Reward>> =
        dataSource.rewards
}
