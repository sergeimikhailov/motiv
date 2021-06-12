package nz.mikhailov.motiv

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import nz.mikhailov.motiv.repository.TransactionRecord
import nz.mikhailov.motiv.repository.TransactionRecordDao

@Database(entities = [TransactionRecord::class], version = 1, exportSchema = false)
abstract class MotivRoomDatabase : RoomDatabase() {

    abstract fun transactionRecordDao(): TransactionRecordDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: RoomDatabase? = null

        fun getDatabase(context: Context): RoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MotivRoomDatabase::class.java,
                    "motiv_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
