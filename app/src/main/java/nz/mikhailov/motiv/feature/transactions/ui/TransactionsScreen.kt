package nz.mikhailov.motiv.feature.transactions.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import nz.mikhailov.motiv.feature.transactions.TransactionsViewModel
import nz.mikhailov.motiv.feature.transactions.ui.model.RewardUIO
import nz.mikhailov.motiv.feature.transactions.ui.model.TransactionUIO
import nz.mikhailov.motiv.ui.theme.MotivTheme

@Composable
fun TransactionsScreen(
    modifier: Modifier = Modifier,
    viewModel: TransactionsViewModel = viewModel(),
) {
    val transactions by viewModel.transactions.observeAsState(emptyList())
    val addTransaction = viewModel::deposit
    val withdraw = viewModel::withdraw
    TransactionsScreenLayout(
        modifier = modifier,
        transactions = transactions,
        addTransaction = addTransaction,
        withdraw = withdraw,
    )
}

@Composable
fun TransactionsScreenLayout(
    modifier: Modifier = Modifier,
    transactions: List<TransactionUIO>,
    addTransaction: (RewardUIO) -> Unit,
    withdraw: (Int) -> Unit,
) {
    val showWithdrawDialog = remember { mutableStateOf(false) }
    Column(
        modifier = modifier,
    ) {
        Balance(
            modifier = Modifier.semantics { contentDescription = "Current balance" },
            balance = transactions.sumOf { it.reward.amount },
        )
        Row(
            Modifier
                .padding(top = 32.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            RewardButton(
                modifier = Modifier
                    .semantics { contentDescription = "Add coding reward" },
                reward = RewardUIO.Code(1),
                onClick = addTransaction,
            )
            RewardButton(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .semantics { contentDescription = "Add exercise reward" },
                reward = RewardUIO.Exercise(1),
                onClick = addTransaction,
            )
            RewardButton(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .semantics { contentDescription = "Add study reward" },
                reward = RewardUIO.Study(2),
                onClick = addTransaction,
            )
        }
        Button(
            modifier = Modifier
                .padding(top = 32.dp)
                .semantics { contentDescription = "Withdraw" },
            onClick = {
                showWithdrawDialog.value = true
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondary,
            ),
        ) {
            Text(text = "Withdraw...")
        }
        Text(
            modifier = Modifier.padding(top = 32.dp),
            text = "History:",
            style = MaterialTheme.typography.h5,
        )
        Transactions(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            transactions = transactions.sortedByDescending(TransactionUIO::date),
        )
        if (showWithdrawDialog.value) {
            WithdrawDialog(
                onConfirm = {
                    withdraw(it)
                    showWithdrawDialog.value = false
                },
                onCancel = {
                    showWithdrawDialog.value = false
               },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionsScreenPreview() {
    MotivTheme {
        Surface {
            TransactionsScreenLayout(
                modifier = Modifier.padding(16.dp),
                transactions = listOf(
                    TransactionUIO(
                        reward = RewardUIO.Study(1),
                        date = "2021-10-07 19:31",
                    )),
                addTransaction = {},
                withdraw = {},
            )
        }
    }
}