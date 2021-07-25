package nz.mikhailov.motiv.feature.transactions.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionRecord(
    @PrimaryKey @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "amount") val amount: Int,
)
