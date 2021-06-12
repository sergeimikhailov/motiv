package nz.mikhailov.motiv.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionRecordDao {

    @Query("SELECT * FROM transactions ORDER BY date DESC")
    fun getTransactions(): Flow<List<TransactionRecord>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(transactionRecord: TransactionRecord)
}
