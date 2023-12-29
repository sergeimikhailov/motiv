package nz.mikhailov.motiv.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import nz.mikhailov.motiv.feature.transactions.data.LocalTransactionDataStore
import nz.mikhailov.motiv.feature.transactions.data.model.TransactionRecord

@Database(
    entities = [
        TransactionRecord::class,
    ],
    version = 4,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 2, to = 3),
    ],
)
@TypeConverters(RoomDatabaseConverters::class)
abstract class MotivRoomDatabase : RoomDatabase() {
    abstract fun transactionRecordDao(): LocalTransactionDataStore
}
