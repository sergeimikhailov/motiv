package nz.mikhailov.motiv.feature.transactions.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nz.mikhailov.motiv.feature.transactions.ui.model.Transaction
import nz.mikhailov.motiv.ui.theme.MotivTheme
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun Transactions(
    modifier: Modifier = Modifier,
    transactions: List<Transaction>,
) {
    val dateFormat = remember { SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()) }
    LazyColumn(
        modifier = modifier,
    ) {
        items(transactions) { transaction ->
            Text(text = "${dateFormat.format(transaction.date)} - $${transaction.amount}")
        }
    }
}

@Preview
@Composable
fun TransactionsPreview() {
    MotivTheme {
        Surface {
            Transactions(
                transactions = listOf(
                    Transaction(1, Date()),
                    Transaction(2, Date()),
                ),
            )
        }
    }
}
