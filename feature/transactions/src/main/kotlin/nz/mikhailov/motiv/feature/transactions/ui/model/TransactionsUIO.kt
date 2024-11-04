package nz.mikhailov.motiv.feature.transactions.ui.model

import nz.mikhailov.motiv.feature.transactions.business.model.Transaction

data class TransactionsUIO(
    val balance: Int,
    val transactions: List<TransactionUIO>,
)

fun List<Transaction>.toUIO() = TransactionsUIO(
    balance = firstOrNull()?.balance ?: 0,
    transactions = map(Transaction::toUIO),
)
