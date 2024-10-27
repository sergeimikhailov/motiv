package nz.mikhailov.motiv.feature.transactions.business

import nz.mikhailov.motiv.data.rewards.HardcodedRewardDataSource
import javax.inject.Inject

class GetRewardsUseCase @Inject constructor(
    private val dataSource: HardcodedRewardDataSource,
) {

    operator fun invoke() =
        dataSource.rewards
}
