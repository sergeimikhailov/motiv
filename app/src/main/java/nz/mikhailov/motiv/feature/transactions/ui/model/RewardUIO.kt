package nz.mikhailov.motiv.feature.transactions.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeveloperMode
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.School
import androidx.compose.ui.graphics.vector.ImageVector
import nz.mikhailov.motiv.feature.transactions.business.model.Transaction
import nz.mikhailov.motiv.feature.transactions.ui.model.RewardUIO.*

sealed interface RewardUIO {
    val amount: Int
    val icon: ImageVector
    val iconString: String
        get() = icon.name

    data class Exercise(
        override val amount: Int,
        override val icon: ImageVector = Icons.Filled.FitnessCenter): RewardUIO

    data class Study(
        override val amount: Int,
        override val icon: ImageVector = Icons.Filled.School): RewardUIO

    data class Code(
        override val amount: Int,
        override val icon: ImageVector = Icons.Filled.DeveloperMode): RewardUIO

    data class Unknown(
        override val amount: Int,
        override val icon: ImageVector = Icons.Filled.Help): RewardUIO
}

fun Transaction.toRewardUIO() = when (activity) {
    Exercise(0).iconString -> Exercise(amount)
    Study(0).iconString -> Study(amount)
    Code(0).iconString -> Code(amount)
    else -> Unknown(amount)
}
