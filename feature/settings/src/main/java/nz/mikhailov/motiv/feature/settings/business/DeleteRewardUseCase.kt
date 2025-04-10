package nz.mikhailov.motiv.feature.settings.business

import nz.mikhailov.motiv.data.rewards.RewardRepository
import javax.inject.Inject

class DeleteRewardUseCase @Inject constructor(
    private val repository: RewardRepository,
) {
    suspend operator fun invoke(id: String) = repository.delete(id)
}