package nz.mikhailov.motiv

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import nz.mikhailov.motiv.data.weight.WeightDatabase
import nz.mikhailov.motiv.database.MIGRATION_1_2
import nz.mikhailov.motiv.database.MIGRATION_3_4
import nz.mikhailov.motiv.database.MotivRoomDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesMotivRoomDatabase(@ApplicationContext context: Context) = Room
        .databaseBuilder(
            context.applicationContext,
            MotivRoomDatabase::class.java,
            "motiv_database")
        .addMigrations(MIGRATION_1_2)
        .addMigrations(MIGRATION_3_4)
        .build()

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
    fun provideTransactionDataStore(database: MotivRoomDatabase) = database.transactionRecordDao()

    @Provides
    @Singleton
    fun provideWeightRecordDataStore(database: WeightDatabase) = database.weightRecordDao()
}