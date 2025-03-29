package nz.mikhailov.motiv.data.rewards

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RewardsModule {

    @Provides
    @Singleton
    fun provideRewardRoomDatabase(@ApplicationContext context: Context) = Room
        .databaseBuilder(
            context.applicationContext,
            RewardDatabase::class.java,
            "reward_database"
        )
        .addCallback(RewardDatabase.initialData)
        .build()

    @Provides
    @Singleton
    fun provideRewardDataStore(database: RewardDatabase) = database.rewardDao()
}