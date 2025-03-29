package nz.mikhailov.motiv.data.rewards

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
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
    
    companion object {
        val initialData = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                // Pre-populate with initial rewards
                val initialRewards = listOf(
                    ContentValues().apply {
                        put(RewardRecord::id.name, "Filled.DeveloperMode")
                        put(RewardRecord::icon.name, "Filled.DeveloperMode")
                        put(RewardRecord::name.name, "Coding reward")
                        put(RewardRecord::cost.name, 100) // $1.00
                    },
                    ContentValues().apply {
                        put(RewardRecord::id.name, "Filled.FitnessCenter")
                        put(RewardRecord::icon.name, "Filled.FitnessCenter")
                        put(RewardRecord::name.name, "Exercise reward")
                        put(RewardRecord::cost.name, 100) // $1.00
                    },
                    ContentValues().apply {
                        put(RewardRecord::id.name, "Filled.School")
                        put(RewardRecord::icon.name, "Filled.School")
                        put(RewardRecord::name.name, "Study reward")
                        put(RewardRecord::cost.name, 200) // $2.00
                    },
                    ContentValues().apply {
                        put(RewardRecord::id.name, "Filled.NoFood")
                        put(RewardRecord::icon.name, "Filled.NoFood")
                        put(RewardRecord::name.name, "No junk food reward")
                        put(RewardRecord::cost.name, 100) // $1.00
                    }
                )

                // Insert all initial rewards
                initialRewards.forEach { values ->
                    db.insert("rewards", SQLiteDatabase.CONFLICT_REPLACE, values)
                }
            }
        }
    }
}
