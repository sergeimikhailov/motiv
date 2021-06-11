package nz.mikhailov.motiv

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    var transactions: List<Transaction> by mutableStateOf(listOf())
        private set

    fun addTransaction(transaction: Transaction) {
        transactions = transactions + listOf(transaction)
    }
}
