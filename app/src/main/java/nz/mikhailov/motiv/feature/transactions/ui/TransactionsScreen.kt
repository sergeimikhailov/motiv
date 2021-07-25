package nz.mikhailov.motiv.feature.transactions.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import nz.mikhailov.motiv.MainViewModel
import nz.mikhailov.motiv.feature.transactions.ui.model.Transaction
import java.util.*

@Composable
fun TransactionsScreen(mainViewModel: MainViewModel) {
    val transactions by mainViewModel.transactions.observeAsState(emptyList())
    val addTransaction = { amount: Int ->
        mainViewModel.addTransaction(Transaction(amount, Date()))
        Unit
    }
    MainScreen(transactions, addTransaction)
}