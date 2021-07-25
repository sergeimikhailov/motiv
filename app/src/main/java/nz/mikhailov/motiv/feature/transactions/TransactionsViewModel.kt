package nz.mikhailov.motiv.feature.transactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import nz.mikhailov.motiv.feature.transactions.data.TransactionRepository
import nz.mikhailov.motiv.feature.transactions.data.model.TransactionRecord
import nz.mikhailov.motiv.feature.transactions.ui.model.Transaction
import java.text.SimpleDateFormat
import java.util.*

class TransactionsViewModel(
    private val transactionRepository: TransactionRepository = TransactionRepository(),
) : ViewModel() {

    private val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

    val transactions: LiveData<List<Transaction>> = transactionRepository
        .transactionRecords
        .map { records -> records.map { record ->
            @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
            (Transaction(
        record.amount,
        sdf.parse(record.date)
    ))
        } }
        .asLiveData()

    fun addTransaction(transaction: Transaction) = viewModelScope.launch {
        transactionRepository.insert(TransactionRecord(sdf.format(transaction.date), transaction.amount))
    }
}
