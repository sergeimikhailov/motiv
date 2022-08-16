package nz.mikhailov.motiv.feature.transactions.business

import kotlinx.coroutines.flow.map
import nz.mikhailov.motiv.feature.transactions.business.model.toBo
import nz.mikhailov.motiv.feature.transactions.data.TransactionRepository
import nz.mikhailov.motiv.feature.transactions.data.model.TransactionRecord

class GetLatestTransactionsUseCase(
    private val repository: TransactionRepository = TransactionRepository(),
) {

    operator fun invoke() = repository
        .latestTransactions(limit = 100)
        .map { records -> records.map(TransactionRecord::toBo) }
}
