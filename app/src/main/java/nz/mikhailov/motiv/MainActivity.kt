package nz.mikhailov.motiv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nz.mikhailov.motiv.ui.theme.MotivTheme
import nz.mikhailov.motiv.ui.theme.Typography
import java.util.*


class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

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
    val transactions = mainViewModel.transactions
    val addTransaction = { mainViewModel.addTransaction(Transaction(1, Date())) }
    MainScreen(transactions, addTransaction)
}

@Composable
fun MainScreen(
    transactions: List<Transaction>,
    addTransaction: () -> Unit
) {
    Scaffold(
        topBar = { MyTopBar() }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Balance(transactions.sumBy(Transaction::amount))
            Button(
                modifier = Modifier.padding(top = 32.dp),
                onClick = addTransaction,
            ) {
                Text(text = "Add $1")
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
fun Transactions(transactions: List<Transaction>) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(transactions) { transaction ->
            Text(text = "${transaction.date} - $${transaction.amount}")
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