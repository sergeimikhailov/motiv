package nz.mikhailov.motiv.feature.transactions.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import nz.mikhailov.motiv.feature.transactions.data.model.TransactionRecord

@Dao
interface LocalTransactionDataStore {

    @Query("SELECT * FROM transactions ORDER BY date DESC LIMIT :limit")
    fun read(limit: Int): Flow<List<TransactionRecord>>

    @Query("SELECT * FROM transactions ORDER BY date DESC LIMIT 1")
    suspend fun readLatest(): TransactionRecord?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(transactionRecord: TransactionRecord)
}
