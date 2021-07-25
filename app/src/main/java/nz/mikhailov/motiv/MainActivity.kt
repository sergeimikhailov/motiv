package nz.mikhailov.motiv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import nz.mikhailov.motiv.feature.transactions.ui.TransactionsScreen
import nz.mikhailov.motiv.ui.theme.MotivTheme


class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as MotivApplication).transactionRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(mainViewModel)
        }
    }

}

@Composable
private fun App(viewModel: MainViewModel) {
    MotivTheme {
        // A surface container using the 'background' color from the theme
        Surface {
            TransactionsScreen(viewModel)
        }
    }
}

@Composable
fun MyTopBar() {
    TopAppBar(
        title = { Text("Motiv") },
    )
}
