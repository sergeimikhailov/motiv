package nz.mikhailov.motiv.data.weight

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(tableName = "weight")
data class WeightRecord(
    @PrimaryKey @ColumnInfo(name = "date") val date: Instant,
    @ColumnInfo(name = "weight") val weight: Int,
)
