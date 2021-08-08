package nz.mikhailov.motiv.feature.transactions.business.model

import nz.mikhailov.motiv.feature.transactions.data.model.TransactionRecord
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class Transaction(val amount: Int, val date: String)

private val dateFormat: DateTimeFormatter = DateTimeFormatter
    .ofPattern("yyyy-MM-dd HH:mm")
    .withZone(ZoneId.systemDefault())

fun TransactionRecord.toBo() =
    Transaction(
        amount = amount,
        date = dateFormat.format(date),
    )