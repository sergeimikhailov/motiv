package nz.mikhailov.motiv.data.rewards

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import nz.mikhailov.motiv.database.RoomDatabaseConverters

@Database(
    entities = [
        RewardRecord::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(RoomDatabaseConverters::class)
abstract class RewardDatabase : RoomDatabase() {
    abstract fun rewardDao(): LocalRewardDataStore
}
