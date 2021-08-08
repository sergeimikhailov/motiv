package nz.mikhailov.motiv.feature.transactions.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nz.mikhailov.motiv.feature.transactions.business.model.Transaction
import nz.mikhailov.motiv.ui.theme.Green
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
            TransactionItem(
                transaction = transaction,
            )
        }
    }
}

@Composable
private fun TransactionItem(
    modifier: Modifier = Modifier,
    transaction: Transaction,
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = "${transaction.date}")
        Text(
            text = "$${transaction.amount}",
            color = Green,
        )
    }
}

@Preview
@Composable
fun TransactionsPreview() {
    MotivTheme {
        Surface {
            Transactions(
                transactions = listOf(
                    Transaction(1, "8 August 2021 13:24"),
                    Transaction(2, "23 July 2021 19:31"),
                ),
            )
        }
    }
}
