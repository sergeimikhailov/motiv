package nz.mikhailov.motiv.feature.transactions.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeveloperMode
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.School
import androidx.compose.ui.graphics.vector.ImageVector

sealed interface RewardUIO {
    val amount: Int
    val icon: ImageVector

    data class Exercise(
        override val amount: Int,
        override val icon: ImageVector = Icons.Filled.FitnessCenter): RewardUIO

    data class Study(
        override val amount: Int,
        override val icon: ImageVector = Icons.Filled.School): RewardUIO

    data class Code(
        override val amount: Int,
        override val icon: ImageVector = Icons.Filled.DeveloperMode): RewardUIO
}
