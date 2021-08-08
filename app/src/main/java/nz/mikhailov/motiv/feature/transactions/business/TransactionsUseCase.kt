package nz.mikhailov.motiv.feature.transactions.business

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nz.mikhailov.motiv.feature.transactions.TransactionsFeature
import nz.mikhailov.motiv.feature.transactions.business.model.Transaction
import nz.mikhailov.motiv.feature.transactions.business.model.toBo
import nz.mikhailov.motiv.feature.transactions.data.TransactionRepository
import nz.mikhailov.motiv.feature.transactions.data.model.TransactionRecord
import java.time.Instant

class TransactionsUseCase(
    private val transactionRepository: TransactionRepository = TransactionRepository(),
) : TransactionsFeature {
    override suspend fun getLatestTransactions(): Flow<List<Transaction>> =
        transactionRepository.transactionRecords
            .map { records -> records.map(TransactionRecord::toBo) }

    override suspend fun deposit(amount: Int) =
        transactionRepository
            .insert(TransactionRecord(Instant.now(), amount))
}
