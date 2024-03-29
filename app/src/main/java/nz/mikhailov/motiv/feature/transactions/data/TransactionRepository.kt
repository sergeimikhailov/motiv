package nz.mikhailov.motiv.feature.transactions.data

import nz.mikhailov.motiv.feature.transactions.data.model.TransactionRecord
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val dataStore: LocalTransactionDataStore,
) {
    fun latestTransactions(limit: Int = 100) = dataStore.read(limit)

    suspend fun latestTransaction() = dataStore.readLatest()

    suspend fun insert(transactionRecord: TransactionRecord) {
        dataStore.insert(transactionRecord)
    }
}
