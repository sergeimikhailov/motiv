package nz.mikhailov.motiv.feature.transactions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nz.mikhailov.motiv.feature.transactions.data.TransactionRepository
import nz.mikhailov.motiv.feature.transactions.data.model.TransactionRecord
import nz.mikhailov.motiv.feature.transactions.ui.model.Transaction
import java.text.SimpleDateFormat
import java.util.*

class TransactionsFeature(
    private val transactionRepository: TransactionRepository = TransactionRepository(),
) {
    private val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

    suspend fun getLatestTransactions(): Flow<List<Transaction>> =
        transactionRepository.transactionRecords
            .map { records ->
                records.map { record ->
                    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
                    (Transaction(
                        record.amount,
                        sdf.parse(record.date)
                    ))
                }
            }

    suspend fun deposit(transaction: Transaction) =
        transactionRepository
            .insert(TransactionRecord(sdf.format(transaction.date), transaction.amount))
}