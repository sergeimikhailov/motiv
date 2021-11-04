package nz.mikhailov.motiv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun App() {
    val navController = rememberNavController()

    MotivTheme {
        Surface {
            Scaffold { innerPadding ->
                NavHost(navController = navController, startDestination = "transactions") {
                    composable("transactions") {
                        TransactionsScreen(
                            modifier = Modifier.padding(innerPadding),
                        )
                    }
                }
            }
        }
    }
}
