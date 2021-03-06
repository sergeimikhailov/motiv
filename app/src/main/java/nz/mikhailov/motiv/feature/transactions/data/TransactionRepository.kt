package nz.mikhailov.motiv.feature.transactions.data

import androidx.annotation.WorkerThread
import nz.mikhailov.motiv.Singletons
import nz.mikhailov.motiv.feature.transactions.data.model.TransactionRecord

@Suppress("RedundantSuspendModifier")
@WorkerThread
class TransactionRepository(
    private val dataStore: LocalTransactionDataStore = Singletons.roomDatabase.transactionRecordDao(),
) {

    suspend fun latestTransactions(limit: Int = 100) = dataStore.read(limit)

    suspend fun latestTransaction() = dataStore.readLatest()

    suspend fun insert(transactionRecord: TransactionRecord) {
        dataStore.insert(transactionRecord)
    }
}
