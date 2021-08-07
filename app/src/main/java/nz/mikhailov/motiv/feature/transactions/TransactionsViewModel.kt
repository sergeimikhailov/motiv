package nz.mikhailov.motiv.feature.transactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import nz.mikhailov.motiv.feature.transactions.ui.model.Transaction

class TransactionsViewModel(
    private val feature: TransactionsFeature = TransactionsFeature(),
) : ViewModel() {

    val transactions: LiveData<List<Transaction>> get() = _transactions
    private val _transactions = MutableLiveData<List<Transaction>>(emptyList())

    init {
        viewModelScope.launch {
            feature.getLatestTransactions().collect {
                _transactions.value = it
            }
        }
    }

    fun deposit(amount: Int) = viewModelScope.launch {
        feature.deposit(amount)
    }
}
