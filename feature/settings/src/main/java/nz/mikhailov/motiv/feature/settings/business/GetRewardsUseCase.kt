package nz.mikhailov.motiv.feature.settings.business

import kotlinx.coroutines.flow.Flow
import nz.mikhailov.motiv.data.rewards.Reward
import nz.mikhailov.motiv.data.rewards.RewardRepository
import javax.inject.Inject

class GetRewardsUseCase @Inject constructor(
    private val repository: RewardRepository,
) {

    operator fun invoke(): Flow<List<Reward>> = repository.rewards
}
