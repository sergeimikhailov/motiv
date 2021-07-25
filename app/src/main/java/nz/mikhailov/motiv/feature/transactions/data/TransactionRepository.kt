package nz.mikhailov.motiv.feature.transactions.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import nz.mikhailov.motiv.feature.transactions.data.model.TransactionRecord

class TransactionRepository(private val transactionDataStore: LocalTransactionDataStore) {

    val transactionRecords: Flow<List<TransactionRecord>> = transactionDataStore.getTransactions()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(transactionRecord: TransactionRecord) {
        transactionDataStore.insert(transactionRecord)
    }
}
