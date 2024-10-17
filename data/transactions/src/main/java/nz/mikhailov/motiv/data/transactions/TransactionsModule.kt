package nz.mikhailov.motiv.data.transactions

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
object TransactionsModule {

    @Provides
    @Singleton
    fun providesMotivRoomDatabase(@ApplicationContext context: Context) = Room
        .databaseBuilder(
            context.applicationContext,
            TransactionsDatabase::class.java,
            "motiv_database") // historic reasons
        .addMigrations(MIGRATION_1_2)
        .addMigrations(MIGRATION_3_4)
        .build()

    @Provides
    @Singleton
    fun provideTransactionDataStore(database: TransactionsDatabase) = database.transactionRecordDao()
}