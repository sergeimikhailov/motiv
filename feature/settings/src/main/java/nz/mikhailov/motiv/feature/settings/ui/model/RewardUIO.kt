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
    val id: String,
    val amount: Double,
    val icon: ImageVector,
    val description: String,
)

fun List<Reward>.toUIO() = map { it.toUIO() }

fun Reward.toUIO() = when (icon) {
    Code -> RewardUIO(
        id = id,
        amount = amount,
        icon = Icons.Filled.DeveloperMode,
        description = name,
    )
    Exercise -> RewardUIO(
        id = id,
        amount = amount,
        icon = Icons.Filled.FitnessCenter,
        description = name,
    )
    Study -> RewardUIO(
        id = id,
        amount = amount,
        icon = Icons.Filled.School,
        description = name,
    )
    NoJunkFood -> RewardUIO(
        id = id,
        amount = amount,
        icon = Icons.Filled.NoFood,
        description = name,
    )
}

fun List<RewardUIO>.getRewardById(id: String): RewardUIO? =
    find { it.id == id }
