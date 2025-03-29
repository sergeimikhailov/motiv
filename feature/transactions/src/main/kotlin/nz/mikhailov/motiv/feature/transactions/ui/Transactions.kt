package nz.mikhailov.motiv.feature.transactions.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nz.mikhailov.motiv.core.common.formatAsCurrency
import nz.mikhailov.motiv.core.design.theme.MotivTheme
import nz.mikhailov.motiv.feature.transactions.ui.model.RewardUIO
import nz.mikhailov.motiv.feature.transactions.ui.model.TransactionUIO

@Composable
fun Transactions(
    modifier: Modifier = Modifier,
    transactions: List<TransactionUIO>,
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
    transaction: TransactionUIO,
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = transaction.date)
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (transaction.reward.amount > 0) {
                Icon(
                    imageVector = transaction.reward.icon,
                    contentDescription = null, // decorative
                )
            }
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = transaction.reward.amount.formatAsCurrency(),
                color = MaterialTheme.colorScheme.secondary,
            )
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
                    TransactionUIO(
                        reward = RewardUIO.Unknown(-3.0),
                        date = "3 October 2021 at 1:16 pm",
                    ),
                    TransactionUIO(
                        reward = RewardUIO.Exercise(1.0),
                        date = "8 August 2021 at 1:24 pm",
                    ),
                    TransactionUIO(
                        reward = RewardUIO.Study(2.0),
                        date = "23 July 2021 at 7:31 am",
                    )
                ),
            )
        }
    }
}
