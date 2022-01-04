package nz.mikhailov.motiv.feature.transactions.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeveloperMode
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.School
import androidx.compose.ui.graphics.vector.ImageVector
import nz.mikhailov.motiv.feature.rewards.business.model.Reward
import nz.mikhailov.motiv.feature.transactions.business.model.Transaction
import nz.mikhailov.motiv.feature.transactions.ui.model.RewardUIO.*

sealed interface RewardUIO {
    val amount: Int
    val icon: ImageVector
    val iconString: String
        get() = icon.name
    val actionDescription: String

    data class Exercise(
        override val amount: Int,
        override val icon: ImageVector = Icons.Filled.FitnessCenter,
        override val actionDescription: String = "Add exercise reward"): RewardUIO

    data class Study(
        override val amount: Int,
        override val icon: ImageVector = Icons.Filled.School,
        override val actionDescription: String = "Add study reward"): RewardUIO

    data class Code(
        override val amount: Int,
        override val icon: ImageVector = Icons.Filled.DeveloperMode,
        override val actionDescription: String = "Add coding reward"): RewardUIO

    data class Unknown(
        override val amount: Int,
        override val icon: ImageVector = Icons.Filled.Help,
        override val actionDescription: String = ""): RewardUIO
}

fun Transaction.toRewardUIO() = when (activity) {
    Exercise(0).iconString -> Exercise(amount)
    Study(0).iconString -> Study(amount)
    Code(0).iconString -> Code(amount)
    else -> Unknown(amount)
}

fun List<Reward>.toUIO() = map { it.toUIO() }

fun Reward.toUIO() = when (this) {
    is Reward.Code -> Code(amount)
    is Reward.Exercise -> Exercise(amount)
    is Reward.Study -> Study(amount)
}
