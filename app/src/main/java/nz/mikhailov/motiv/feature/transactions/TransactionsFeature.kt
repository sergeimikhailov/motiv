package nz.mikhailov.motiv.feature.transactions

import kotlinx.coroutines.flow.Flow
import nz.mikhailov.motiv.feature.rewards.business.model.Reward
import nz.mikhailov.motiv.feature.transactions.business.DepositUseCase
import nz.mikhailov.motiv.feature.transactions.business.GetLatestTransactionsUseCase
import nz.mikhailov.motiv.feature.transactions.business.GetRewardsUseCase
import nz.mikhailov.motiv.feature.transactions.business.WithdrawUseCase
import nz.mikhailov.motiv.feature.transactions.business.model.Transaction
import javax.inject.Inject

class TransactionsFeature @Inject constructor(
    private val getRewardsUseCase: GetRewardsUseCase,
    private val getLatestTransactionsUseCase: GetLatestTransactionsUseCase,
    private val depositUseCase: DepositUseCase,
    private val withdrawUseCase: WithdrawUseCase,
) {
    fun getRewards(): Flow<List<Reward>> = getRewardsUseCase()
    fun getLatestTransactions(): Flow<List<Transaction>> = getLatestTransactionsUseCase()
    suspend fun deposit(amount: Int, activity: String) = depositUseCase(amount, activity)
    suspend fun withdraw(amount: Int) = withdrawUseCase(amount)
}
