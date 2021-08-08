package nz.mikhailov.motiv.feature.transactions

import kotlinx.coroutines.flow.Flow
import nz.mikhailov.motiv.feature.transactions.business.TransactionsUseCase
import nz.mikhailov.motiv.feature.transactions.ui.model.Transaction

interface TransactionsFeature {
    suspend fun getLatestTransactions(): Flow<List<Transaction>>
    suspend fun deposit(amount: Int)
}

class TransactionsFacade private constructor(
    private val useCase: TransactionsUseCase = TransactionsUseCase(),
): TransactionsFeature by useCase {
    companion object {
        fun newInstance() = TransactionsFacade()
    }
}
