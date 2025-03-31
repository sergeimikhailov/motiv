package nz.mikhailov.motiv.feature.settings.business

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeveloperMode
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.NoFood
import androidx.compose.material.icons.filled.School
import nz.mikhailov.motiv.data.rewards.Reward
import nz.mikhailov.motiv.data.rewards.RewardIcon
import nz.mikhailov.motiv.data.rewards.RewardRepository
import nz.mikhailov.motiv.feature.settings.ui.model.RewardUIO
import java.util.UUID
import javax.inject.Inject

class CreateRewardUseCase @Inject constructor(
    private val repository: RewardRepository,
) {
    suspend operator fun invoke(reward: RewardUIO) =
        repository.create(reward.toBo() 
            ?: throw IllegalArgumentException("Invalid reward icon"))

    private fun RewardUIO.toBo(): Reward? {
        return Reward(
            id = UUID.randomUUID().toString(),
            name = description,
            amount = amount,
            icon = when (this.icon) {
                Icons.Filled.DeveloperMode -> RewardIcon.Code
                Icons.Filled.FitnessCenter -> RewardIcon.Exercise
                Icons.Filled.School -> RewardIcon.Study
                Icons.Filled.NoFood -> RewardIcon.NoJunkFood
                else -> return null
            }
        )
    }
}
