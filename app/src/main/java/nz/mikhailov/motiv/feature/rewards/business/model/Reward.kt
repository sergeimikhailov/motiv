package nz.mikhailov.motiv.feature.rewards.business.model

sealed class Reward {
    abstract val amount: Int

    data class Code(override val amount: Int): Reward()
    data class Exercise(override val amount: Int): Reward()
    data class Study(override val amount: Int): Reward()
    data class NoJunkFood(override val amount: Int): Reward()
}
