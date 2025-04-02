package nz.mikhailov.motiv.data.rewards

data class Reward(
    val id: String,
    val name: String,
    val amount: Double,
    val icon: RewardIcon,
)

enum class RewardIcon(val value: String) {
    Code("Filled.DeveloperMode"),
    Exercise("Filled.FitnessCenter"), 
    Study("Filled.School"),
    NoJunkFood("Filled.NoFood");

    companion object {
        fun fromString(value: String) = when (value) {
            "Filled.DeveloperMode" -> Code
            "Filled.FitnessCenter" -> Exercise
            "Filled.School" -> Study
            "Filled.NoFood" -> NoJunkFood
            else -> null
        }
    }
}
