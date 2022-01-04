package nz.mikhailov.motiv.feature.transactions

import kotlinx.coroutines.flow.Flow
import nz.mikhailov.motiv.feature.rewards.business.model.Reward
import nz.mikhailov.motiv.feature.transactions.business.GetRewardsUseCase
import nz.mikhailov.motiv.feature.transactions.business.TransactionsUseCase
import nz.mikhailov.motiv.feature.transactions.business.model.Transaction

interface TransactionsFeature {
    suspend fun getRewards(): Flow<List<Reward>>
    suspend fun getLatestTransactions(): Flow<List<Transaction>>
    suspend fun deposit(amount: Int, activity: String)
    suspend fun withdraw(amount: Int)
}

class TransactionsFacade private constructor(
    private val useCase: TransactionsUseCase = TransactionsUseCase(),
    private val getRewardsUseCase: GetRewardsUseCase = GetRewardsUseCase(),
): TransactionsFeature {

    companion object {
        fun newInstance() = TransactionsFacade()
    }

    override suspend fun getRewards() = getRewardsUseCase()

    override suspend fun getLatestTransactions() = useCase.getLatestTransactions()

    override suspend fun deposit(amount: Int, activity: String) = useCase.deposit(amount, activity)

    override suspend fun withdraw(amount: Int) = useCase.withdraw(amount)
}
