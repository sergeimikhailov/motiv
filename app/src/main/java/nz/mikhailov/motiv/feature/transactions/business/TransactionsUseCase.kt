package nz.mikhailov.motiv.feature.transactions.business

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import nz.mikhailov.motiv.feature.transactions.TransactionsFeature
import nz.mikhailov.motiv.feature.transactions.business.model.toBo
import nz.mikhailov.motiv.feature.transactions.data.TransactionRepository
import nz.mikhailov.motiv.feature.transactions.data.model.TransactionRecord
import java.time.Instant

class TransactionsUseCase(
    private val transactionRepository: TransactionRepository = TransactionRepository(),
) : TransactionsFeature {
    
    override suspend fun getLatestTransactions() = withContext(Dispatchers.IO) {
        transactionRepository.transactionRecords
            .map { records -> records.map(TransactionRecord::toBo) }
    }

    override suspend fun deposit(amount: Int, activity: String) = withContext(Dispatchers.IO) {
        transactionRepository
            .insert(TransactionRecord(
                date = Instant.now(),
                amount = amount,
                activity = activity,
            ))
    }
}
