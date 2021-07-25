package nz.mikhailov.motiv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            Scaffold(
                topBar = { MyTopBar() }
            ) { innerPadding ->
                TransactionsScreen(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp),
                    viewModel = viewModel,
                )
            }
        }
    }
}

@Composable
fun MyTopBar() {
    TopAppBar(
        title = { Text("Motiv") },
    )
}
