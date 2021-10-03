package nz.mikhailov.motiv.feature.transactions.data

import androidx.annotation.WorkerThread
import nz.mikhailov.motiv.Singletons
import nz.mikhailov.motiv.feature.transactions.data.model.TransactionRecord

@Suppress("RedundantSuspendModifier")
class TransactionRepository(
    private val transactionDataStore: LocalTransactionDataStore = Singletons.roomDatabase.transactionRecordDao(),
) {

    @WorkerThread
    suspend fun latestTransactions(limit: Int = 100) =
        transactionDataStore.getTransactions(limit)

    @WorkerThread
    suspend fun insert(transactionRecord: TransactionRecord) {
        transactionDataStore.insert(transactionRecord)
    }
}
