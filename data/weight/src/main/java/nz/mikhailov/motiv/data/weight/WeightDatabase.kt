package nz.mikhailov.motiv.data.weight

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import nz.mikhailov.motiv.database.RoomDatabaseConverters

@Database(
    entities = [
        WeightRecord::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(RoomDatabaseConverters::class)
abstract class WeightDatabase : RoomDatabase() {
    abstract fun weightRecordDao(): LocalWeightRecordDataStore
}
