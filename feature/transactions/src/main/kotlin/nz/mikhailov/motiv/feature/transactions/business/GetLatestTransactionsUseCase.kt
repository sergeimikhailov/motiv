package nz.mikhailov.motiv.feature.transactions.business

import kotlinx.coroutines.flow.map
import nz.mikhailov.motiv.data.transactions.TransactionRecord
import nz.mikhailov.motiv.feature.transactions.business.model.toBo
import nz.mikhailov.motiv.data.transactions.TransactionRepository
import javax.inject.Inject

class GetLatestTransactionsUseCase @Inject constructor(
    private val repository: TransactionRepository,
) {

    operator fun invoke() = repository
        .latestTransactions(limit = 100)
        .map { records -> records.map(TransactionRecord::toBo) }
}
