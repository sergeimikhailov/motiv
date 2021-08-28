package nz.mikhailov.motiv.feature.transactions.ui.model

import nz.mikhailov.motiv.feature.transactions.business.model.Transaction

data class TransactionUIO(
    val reward: RewardUIO,
    val date: String,
)

fun Transaction.toUIO() = TransactionUIO(
    reward = toRewardUIO(),
    date = date,
)

fun List<Transaction>.toUIO() = map(Transaction::toUIO)