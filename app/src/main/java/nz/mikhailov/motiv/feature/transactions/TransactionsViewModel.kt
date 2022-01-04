package nz.mikhailov.motiv.feature.transactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import nz.mikhailov.motiv.Features
import nz.mikhailov.motiv.feature.transactions.ui.model.RewardUIO
import nz.mikhailov.motiv.feature.transactions.ui.model.TransactionsUIO
import nz.mikhailov.motiv.feature.transactions.ui.model.toUIO

@OptIn(ExperimentalCoroutinesApi::class)
class TransactionsViewModel(
    private val feature: TransactionsFeature = Features.transactions,
) : ViewModel() {

    val rewards: LiveData<List<RewardUIO>> get() = _rewards
    private val _rewards = MutableLiveData<List<RewardUIO>>()

    val transactions: LiveData<TransactionsUIO> get() = _transactions
    private val _transactions = MutableLiveData<TransactionsUIO>()

    init {
        viewModelScope.launch {
            feature.getRewards()
                .mapLatest { it.toUIO() }
                .collect {
                    _rewards.value = it
                }
        }
        viewModelScope.launch {
            feature.getLatestTransactions()
                .mapLatest { it.toUIO() }
                .collect {
                    _transactions.value = it
                }
        }
    }

    fun deposit(reward: RewardUIO) {
        viewModelScope.launch {
            feature.deposit(
                amount = reward.amount,
                activity = reward.iconString,
            )
        }
    }

    fun withdraw(amount: Int) {
        viewModelScope.launch {
            feature.withdraw(amount)
        }
    }
}
