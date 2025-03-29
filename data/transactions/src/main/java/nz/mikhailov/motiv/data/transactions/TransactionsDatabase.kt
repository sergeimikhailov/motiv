package nz.mikhailov.motiv.data.transactions

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import nz.mikhailov.motiv.database.RoomDatabaseConverters

@Database(
    entities = [
        TransactionRecord::class,
    ],
    version = 7,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 2, to = 3),
        AutoMigration(from = 4, to = 5),
        AutoMigration(from = 5, to = 6, spec = Migration5To6::class),
    ],
)
@TypeConverters(RoomDatabaseConverters::class)
abstract class TransactionsDatabase : RoomDatabase() {
    abstract fun transactionRecordDao(): LocalTransactionDataStore
}
