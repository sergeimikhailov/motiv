package nz.mikhailov.motiv.feature.settings.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeveloperMode
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.NoFood
import androidx.compose.material.icons.filled.School
import androidx.compose.ui.graphics.vector.ImageVector
import nz.mikhailov.motiv.data.rewards.Reward
import nz.mikhailov.motiv.data.rewards.RewardIcon.Code
import nz.mikhailov.motiv.data.rewards.RewardIcon.Exercise
import nz.mikhailov.motiv.data.rewards.RewardIcon.NoJunkFood
import nz.mikhailov.motiv.data.rewards.RewardIcon.Study

data class RewardUIO(
    val amount: Double,
    val icon: ImageVector,
    val description: String,
)

fun List<Reward>.toUIO() = map { it.toUIO() }

fun Reward.toUIO() = when (icon) {
    Code -> RewardUIO(
        amount = amount,
        icon = Icons.Filled.DeveloperMode,
        description = "Coding reward",
    )
    Exercise -> RewardUIO(
        amount = amount,
        icon = Icons.Filled.FitnessCenter,
        description = "Exercise reward",
    )
    Study -> RewardUIO(
        amount = amount,
        icon = Icons.Filled.School,
        description = "Study reward",
    )
    NoJunkFood -> RewardUIO(
        amount = amount,
        icon = Icons.Filled.NoFood,
        description = "No junk food reward",
    )
}
