package nz.mikhailov.motiv.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec
import nz.mikhailov.motiv.feature.transactions.data.LocalTransactionDataStore
import nz.mikhailov.motiv.feature.transactions.data.model.TransactionRecord

@Database(
    entities = [
        TransactionRecord::class,
    ],
    version = 6,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 2, to = 3),
        AutoMigration(from = 4, to = 5),
        AutoMigration(from = 5, to = 6, spec = Migration5To6::class),
    ],
)
@TypeConverters(RoomDatabaseConverters::class)
abstract class MotivRoomDatabase : RoomDatabase() {
    abstract fun transactionRecordDao(): LocalTransactionDataStore
}
