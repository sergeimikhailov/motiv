package nz.mikhailov.motiv.feature.transactions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nz.mikhailov.motiv.feature.transactions.data.TransactionRepository
import nz.mikhailov.motiv.feature.transactions.data.model.TransactionRecord
import nz.mikhailov.motiv.feature.transactions.ui.model.Transaction
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

val dateFormat: DateTimeFormatter = DateTimeFormatter
    .ofPattern("yyyy-MM-dd HH:mm")
    .withZone(ZoneId.systemDefault())

class TransactionsFeature(
    private val transactionRepository: TransactionRepository = TransactionRepository(),
) {
    suspend fun getLatestTransactions(): Flow<List<Transaction>> =
        transactionRepository.transactionRecords
            .map { records ->
                records.map { record ->
                    Transaction(
                        amount = record.amount,
                        date = dateFormat.format(record.date),
                    )
                }
            }

    suspend fun deposit(amount: Int) =
        transactionRepository
            .insert(TransactionRecord(Instant.now(), amount))
}