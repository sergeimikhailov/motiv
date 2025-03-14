package nz.mikhailov.motiv.data.rewards

data class Reward(
    val id: String,
    val name: String,
    val amount: Int,
    val icon: RewardIcon,
)

enum class RewardIcon {
    Code,
    Exercise, 
    Study,
    NoJunkFood;

    companion object {
        fun fromString(value: String): RewardIcon? = when (value.lowercase()) {
            "code" -> Code
            "exercise" -> Exercise
            "study" -> Study
            "nojunkfood" -> NoJunkFood
            else -> null
        }
    }
}