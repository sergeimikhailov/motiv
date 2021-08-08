package nz.mikhailov.motiv.database

import android.content.Context
import androidx.room.*
import nz.mikhailov.motiv.feature.transactions.data.LocalTransactionDataStore
import nz.mikhailov.motiv.feature.transactions.data.model.TransactionRecord

@Database(
    entities = [
        TransactionRecord::class,
    ],
    version = 3,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 2, to = 3),
    ],
)
@TypeConverters(RoomDatabaseConverters::class)
abstract class MotivRoomDatabase : RoomDatabase() {

    abstract fun transactionRecordDao(): LocalTransactionDataStore

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: RoomDatabase? = null

        fun getDatabase(context: Context): RoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        MotivRoomDatabase::class.java,
                        "motiv_database")
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
