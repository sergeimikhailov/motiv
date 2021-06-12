package nz.mikhailov.motiv

import androidx.lifecycle.*
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import nz.mikhailov.motiv.repository.TransactionRecord
import nz.mikhailov.motiv.repository.TransactionRepository
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(private val transactionRepository: TransactionRepository): ViewModel() {

    private val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

    val transactions: LiveData<List<Transaction>> = transactionRepository
        .transactionRecords
        .map { records -> records.map { record ->
            @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
            Transaction(record.amount, sdf.parse(record.date))
        } }
        .asLiveData()

    fun addTransaction(transaction: Transaction) = viewModelScope.launch {
        transactionRepository.insert(TransactionRecord(sdf.format(transaction.date), transaction.amount))
    }
}

class MainViewModelFactory(private val transactionRepository: TransactionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(transactionRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
