package nz.mikhailov.motiv.feature.transactions.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(tableName = "transactions")
data class TransactionRecord(
    @PrimaryKey @ColumnInfo(name = "date") val date: Instant,
    @ColumnInfo(name = "amount") val amount: Int,
    @ColumnInfo(name = "activity") val activity: String? = null,
)
