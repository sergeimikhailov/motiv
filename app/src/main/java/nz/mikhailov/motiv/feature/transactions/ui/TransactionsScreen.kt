package nz.mikhailov.motiv.feature.transactions.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeveloperMode
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.School
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import nz.mikhailov.motiv.feature.transactions.TransactionsViewModel
import nz.mikhailov.motiv.feature.transactions.business.model.Transaction
import nz.mikhailov.motiv.ui.theme.MotivTheme

@Composable
fun TransactionsScreen(
    modifier: Modifier = Modifier,
    viewModel: TransactionsViewModel = viewModel(),
) {
    val transactions by viewModel.transactions.observeAsState(emptyList())
    val addTransaction = viewModel::deposit
    TransactionsScreenLayout(modifier, transactions, addTransaction)
}

@Composable
fun TransactionsScreenLayout(
    modifier: Modifier = Modifier,
    transactions: List<Transaction>,
    addTransaction: (Int) -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        Balance(balance = transactions.sumOf(Transaction::amount))
        Row(
            Modifier
                .padding(top = 32.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            RewardButton(
                icon = Icons.Filled.DeveloperMode,
                onClick = addTransaction,
                rewardAmount = 1
            )
            RewardButton(
                icon = Icons.Filled.FitnessCenter,
                onClick = addTransaction,
                rewardAmount = 1,
                modifier = Modifier.padding(start = 16.dp)
            )
            RewardButton(
                icon = Icons.Filled.School,
                onClick = addTransaction,
                rewardAmount = 2,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Text(
            text = "History:",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(top = 32.dp),
        )
        Transactions(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            transactions = transactions.sortedByDescending(Transaction::date),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionsScreenPreview() {
    MotivTheme {
        Surface {
            TransactionsScreenLayout(
                modifier = Modifier.padding(16.dp),
                transactions = listOf(Transaction(1, "2021-10-07 19:31"))
            ) {}
        }
    }
}