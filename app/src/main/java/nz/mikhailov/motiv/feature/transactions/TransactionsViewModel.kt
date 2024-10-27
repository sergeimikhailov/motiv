package nz.mikhailov.motiv.feature.transactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import nz.mikhailov.motiv.data.rewards.Reward
import nz.mikhailov.motiv.feature.transactions.business.model.Transaction
import nz.mikhailov.motiv.feature.transactions.ui.model.RewardUIO
import nz.mikhailov.motiv.feature.transactions.ui.model.TransactionsUIO
import nz.mikhailov.motiv.feature.transactions.ui.model.toUIO
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val feature: TransactionsFeature,
) : ViewModel() {

    val rewards: LiveData<List<RewardUIO>> =
        feature.getRewards().map(List<Reward>::toUIO).asLiveData()

    val transactions: LiveData<TransactionsUIO> =
        feature.getLatestTransactions().map(List<Transaction>::toUIO).asLiveData()

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
