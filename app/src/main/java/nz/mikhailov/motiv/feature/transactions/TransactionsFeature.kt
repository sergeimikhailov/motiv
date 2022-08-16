package nz.mikhailov.motiv.feature.transactions

import kotlinx.coroutines.flow.Flow
import nz.mikhailov.motiv.feature.rewards.business.model.Reward
import nz.mikhailov.motiv.feature.transactions.business.DepositUseCase
import nz.mikhailov.motiv.feature.transactions.business.GetLatestTransactionsUseCase
import nz.mikhailov.motiv.feature.transactions.business.GetRewardsUseCase
import nz.mikhailov.motiv.feature.transactions.business.WithdrawUseCase
import nz.mikhailov.motiv.feature.transactions.business.model.Transaction

interface TransactionsFeature {
    fun getRewards(): Flow<List<Reward>>
    fun getLatestTransactions(): Flow<List<Transaction>>
    suspend fun deposit(amount: Int, activity: String)
    suspend fun withdraw(amount: Int)
}

class TransactionsFacade private constructor(
    private val getRewardsUseCase: GetRewardsUseCase = GetRewardsUseCase(),
    private val getLatestTransactionsUseCase: GetLatestTransactionsUseCase = GetLatestTransactionsUseCase(),
    private val depositUseCase: DepositUseCase = DepositUseCase(),
    private val withdrawUseCase: WithdrawUseCase = WithdrawUseCase(),
): TransactionsFeature {

    companion object {
        fun newInstance() = TransactionsFacade()
    }

    override fun getRewards() = getRewardsUseCase()

    override fun getLatestTransactions() = getLatestTransactionsUseCase()

    override suspend fun deposit(amount: Int, activity: String) = depositUseCase(amount, activity)

    override suspend fun withdraw(amount: Int) = withdrawUseCase(amount)
}
