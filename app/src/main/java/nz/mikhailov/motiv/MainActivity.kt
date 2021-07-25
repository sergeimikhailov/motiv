package nz.mikhailov.motiv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nz.mikhailov.motiv.feature.transactions.ui.TransactionsScreen
import nz.mikhailov.motiv.ui.theme.MotivTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
private fun App() {
    val navController = rememberNavController()

    MotivTheme {
        // A surface container using the 'background' color from the theme
        Surface {
            Scaffold(
                topBar = { MyTopBar() }
            ) { innerPadding ->
                NavHost(navController = navController, startDestination = "transactions") {
                    composable("transactions") {
                        TransactionsScreen(
                            modifier = Modifier
                                .padding(innerPadding)
                                .padding(16.dp),
                        )
                    }
                }
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
