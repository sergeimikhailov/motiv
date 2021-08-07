package nz.mikhailov.motiv.feature.transactions.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nz.mikhailov.motiv.feature.transactions.ui.model.Transaction
import nz.mikhailov.motiv.ui.theme.MotivTheme

@Composable
fun Transactions(
    modifier: Modifier = Modifier,
    transactions: List<Transaction>,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(transactions) { transaction ->
            Text(text = "${transaction.date} - $${transaction.amount}")
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
                    Transaction(1, "2021-10-07 19:32"),
                    Transaction(2, "2021-10-07 19:31"),
                ),
            )
        }
    }
}
