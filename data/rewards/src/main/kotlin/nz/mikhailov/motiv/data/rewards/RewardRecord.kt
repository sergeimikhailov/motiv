package nz.mikhailov.motiv.data.rewards

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rewards")
data class RewardRecord(
    @PrimaryKey
    val icon: String,
    val name: String,
    val cost: Int,
)
