package nz.mikhailov.motiv.feature.settings.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeveloperMode
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.NoFood
import androidx.compose.material.icons.filled.School
import androidx.compose.ui.graphics.vector.ImageVector
import nz.mikhailov.motiv.data.rewards.Reward

sealed interface RewardUIO {
    val amount: Int
    val icon: ImageVector
    val description: String

    data class Exercise(
        override val amount: Int,
        override val icon: ImageVector = Icons.Filled.FitnessCenter,
        override val description: String = "Exercise reward"): RewardUIO

    data class Study(
        override val amount: Int,
        override val icon: ImageVector = Icons.Filled.School,
        override val description: String = "Study reward"): RewardUIO

    data class Code(
        override val amount: Int,
        override val icon: ImageVector = Icons.Filled.DeveloperMode,
        override val description: String = "Coding reward"): RewardUIO

    data class NoJunkFood(
        override val amount: Int,
        override val icon: ImageVector = Icons.Filled.NoFood,
        override val description: String = "No junk food reward"): RewardUIO
}

fun List<Reward>.toUIO() = map { it.toUIO() }

fun Reward.toUIO() = when (this) {
    is Reward.Code -> RewardUIO.Code(amount)
    is Reward.Exercise -> RewardUIO.Exercise(amount)
    is Reward.Study -> RewardUIO.Study(amount)
    is Reward.NoJunkFood -> RewardUIO.NoJunkFood(amount)
}
