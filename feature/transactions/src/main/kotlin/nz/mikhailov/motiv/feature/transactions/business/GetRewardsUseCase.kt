package nz.mikhailov.motiv.feature.transactions.business

import nz.mikhailov.motiv.data.rewards.RewardRepository
import javax.inject.Inject

class GetRewardsUseCase @Inject constructor(
    private val repository: RewardRepository,
) {

    operator fun invoke() = repository.rewards
}
