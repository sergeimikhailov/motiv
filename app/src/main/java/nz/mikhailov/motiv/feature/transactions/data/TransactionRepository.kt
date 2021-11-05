package nz.mikhailov.motiv.feature.transactions.data

import androidx.annotation.WorkerThread
import nz.mikhailov.motiv.Singletons
import nz.mikhailov.motiv.feature.transactions.data.model.TransactionRecord

@Suppress("RedundantSuspendModifier")
@WorkerThread
class TransactionRepository(
    private val transactionDataStore: LocalTransactionDataStore = Singletons.roomDatabase.transactionRecordDao(),
) {

    suspend fun latestTransactions(limit: Int = 100) =
        transactionDataStore.getTransactions(limit)

    suspend fun insert(transactionRecord: TransactionRecord) {
        transactionDataStore.insert(transactionRecord)
    }
}
