package nz.mikhailov.motiv.feature.transactions.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.hilt.navigation.compose.hiltViewModel
import nz.mikhailov.motiv.core.design.theme.MotivTheme
import nz.mikhailov.motiv.feature.transactions.TransactionsViewModel
import nz.mikhailov.motiv.feature.transactions.ui.model.RewardUIO
import nz.mikhailov.motiv.feature.transactions.ui.model.TransactionUIO
import nz.mikhailov.motiv.feature.transactions.ui.model.TransactionsUIO

@Composable
fun TransactionsScreen(
    modifier: Modifier = Modifier,
    viewModel: TransactionsViewModel = hiltViewModel(),
) {
    val transactions by viewModel.transactions.observeAsState(TransactionsUIO(balance = 0.0, transactions = emptyList()))
    val rewards by viewModel.rewards.observeAsState(emptyList())
    val addTransaction = viewModel::deposit
    val withdraw = viewModel::withdraw
    TransactionsScreenLayout(
        modifier = modifier,
        rewards = rewards,
        transactions = transactions,
        addTransaction = addTransaction,
        withdraw = withdraw,
    )
}

@Composable
fun TransactionsScreenLayout(
    modifier: Modifier = Modifier,
    transactions: TransactionsUIO,
    rewards: List<RewardUIO>,
    addTransaction: (RewardUIO) -> Unit,
    withdraw: (Double) -> Unit,
) {
    val showWithdrawDialog = remember { mutableStateOf(false) }
    Column(
        modifier = modifier.padding(top = 16.dp),
    ) {
        Balance(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .semantics { contentDescription = "Current balance" },
            balance = transactions.balance,
        )
        Row(
            Modifier
                .padding(top = 32.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            rewards.forEach {
                RewardButton(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .semantics { contentDescription = it.actionDescription },
                    reward = it,
                    onClick = addTransaction,
                )
            }
        }
        Button(
            modifier = Modifier
                .padding(top = 32.dp)
                .padding(horizontal = 16.dp)
                .semantics { contentDescription = "Withdraw" },
            onClick = {
                showWithdrawDialog.value = true
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
            ),
        ) {
            Text(text = "Withdraw...")
        }
        Text(
            modifier = Modifier
                .padding(top = 32.dp)
                .padding(horizontal = 16.dp),
            text = "History:",
            style = MaterialTheme.typography.headlineMedium,
        )
        Transactions(
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            transactions = transactions.transactions,
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TransactionsScreenPreview() {
    MotivTheme {
        Surface {
            TransactionsScreenLayout(
                rewards = listOf(
                    RewardUIO.Code(1.0),
                    RewardUIO.Exercise(1.0),
                    RewardUIO.Study(2.0),
                ),
                transactions = TransactionsUIO(
                    balance = 1.0,
                    transactions = listOf(
                        TransactionUIO(
                            reward = RewardUIO.Study(1.0),
                            date = "7 October 2021 at 7:31 pm",
                        )
                    ),
                ),
                addTransaction = {},
                withdraw = {},
            )
        }
    }
}