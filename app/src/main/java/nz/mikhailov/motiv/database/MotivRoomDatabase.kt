package nz.mikhailov.motiv.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import nz.mikhailov.motiv.feature.tracker.data.LocalWeightRecordDataStore
import nz.mikhailov.motiv.feature.tracker.data.WeightRecord
import nz.mikhailov.motiv.feature.transactions.data.LocalTransactionDataStore
import nz.mikhailov.motiv.feature.transactions.data.model.TransactionRecord

@Database(
    entities = [
        TransactionRecord::class,
        WeightRecord::class,
    ],
    version = 5,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 2, to = 3),
        AutoMigration(from = 4, to = 5),
    ],
)
@TypeConverters(RoomDatabaseConverters::class)
abstract class MotivRoomDatabase : RoomDatabase() {
    abstract fun transactionRecordDao(): LocalTransactionDataStore
    abstract fun weightRecordDao(): LocalWeightRecordDataStore
}
