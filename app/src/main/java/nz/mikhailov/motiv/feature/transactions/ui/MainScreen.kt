package nz.mikhailov.motiv.feature.transactions.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeveloperMode
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.School
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nz.mikhailov.motiv.MyTopBar
import nz.mikhailov.motiv.feature.transactions.ui.model.Transaction

@Composable
fun MainScreen(
    transactions: List<Transaction>,
    addTransaction: (Int) -> Unit
) {
    Scaffold(
        topBar = { MyTopBar() }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Balance(transactions.sumOf(Transaction::amount))
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
            Transactions(transactions = transactions.sortedByDescending(Transaction::date))
        }
    }
}