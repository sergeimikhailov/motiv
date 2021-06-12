package nz.mikhailov.motiv.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class TransactionRepository(private val transactionRecordDao: TransactionRecordDao) {

    val transactionRecords: Flow<List<TransactionRecord>> = transactionRecordDao.getTransactions()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(transactionRecord: TransactionRecord) {
        transactionRecordDao.insert(transactionRecord)
    }
}
