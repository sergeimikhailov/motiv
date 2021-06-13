package nz.mikhailov.motiv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeveloperMode
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.School
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nz.mikhailov.motiv.ui.theme.MotivTheme
import nz.mikhailov.motiv.ui.theme.Typography
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as MotivApplication).transactionRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MotivTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    MainActivityScreen(mainViewModel)
                }
            }
        }
    }
}

@Composable
fun MainActivityScreen(mainViewModel: MainViewModel) {
    val transactions by mainViewModel.transactions.observeAsState(emptyList())
    val addTransaction = { amount: Int ->
        mainViewModel.addTransaction(Transaction(amount, Date()))
        Unit
    }
    MainScreen(transactions, addTransaction)
}

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
                    .horizontalScroll(rememberScrollState())) {
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

@Composable
fun RewardButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick: (Int) -> Unit,
    rewardAmount: Int
) {
    Button(
        modifier = modifier,
        onClick = { onClick(rewardAmount) },
    ) {
        Icon(icon, contentDescription = null)
        Text(text = "Add $${rewardAmount}", Modifier.padding(start = 8.dp))
    }
}

@Composable
fun Transactions(transactions: List<Transaction>) {
    val dateFormat = remember { SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()) }
    LazyColumn(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(transactions) { transaction ->
            Text(text = "${dateFormat.format(transaction.date)} - $${transaction.amount}")
        }
    }
}

data class Transaction(val amount: Int, val date: Date)

@Composable
fun MyTopBar() {
    TopAppBar(
        title = { Text("Motiv") },
    )
}

@Composable
fun Balance(balance: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
    ) {
        Text("Total is: ", style = Typography.h3)
        Text("$${balance}", style = Typography.h3)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MotivTheme {
        MainScreen(
            transactions = listOf(Transaction(1, Date())),
            addTransaction = {}
        )
    }
}