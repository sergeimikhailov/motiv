package nz.mikhailov.motiv.data.weight

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
object WeightModule {

    @Provides
    @Singleton
    fun provideWeightRoomDatabase(@ApplicationContext context: Context) = Room
        .databaseBuilder(
            context.applicationContext,
            WeightDatabase::class.java,
            "weight_database")
        .build()

    @Provides
    @Singleton
    fun provideWeightRecordDataStore(database: WeightDatabase) = database.weightRecordDao()
}
