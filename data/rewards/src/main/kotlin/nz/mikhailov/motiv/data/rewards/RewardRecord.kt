package nz.mikhailov.motiv.data.rewards

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rewards")
data class RewardRecord(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "icon") val icon: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "cost") val cost: Int,
)
