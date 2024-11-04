package nz.mikhailov.motiv.data.transactions

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
