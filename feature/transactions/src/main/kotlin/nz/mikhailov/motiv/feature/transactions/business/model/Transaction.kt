package nz.mikhailov.motiv.feature.transactions.business.model

import nz.mikhailov.motiv.data.transactions.TransactionRecord
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

data class Transaction(
    val amount: Double,
    val date: String,
    val activity: String? = null,
    val balance: Double,
)

private val defaultFormatter: DateTimeFormatter = DateTimeFormatter
    .ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT)
    .withZone(ZoneId.systemDefault())

fun TransactionRecord.toBo(formatter: DateTimeFormatter = defaultFormatter) =
    Transaction(
        amount = amount / 100.0,
        date = formatter.format(date),
        activity = activity,
        balance = balance / 100.0,
    )
