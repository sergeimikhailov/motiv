package nz.mikhailov.motiv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import nz.mikhailov.motiv.feature.settings.ui.SettingsScreen
import nz.mikhailov.motiv.feature.transactions.ui.TransactionsScreen
import nz.mikhailov.motiv.ui.NavigationButton
import nz.mikhailov.motiv.ui.theme.MotivTheme

@AndroidEntryPoint
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
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    MotivTheme {
        Surface {
            Scaffold(
                topBar = {
                    SmallTopAppBar(
                        title = { Text("Motiv") },
                        actions = {
                            val isSettingsScreen = "settings" == navBackStackEntry?.destination?.route
                            Crossfade(targetState = isSettingsScreen) {
                                if (!it) {
                                    NavigationButton(
                                        icon = Icons.Outlined.Settings,
                                        onClick = { navController.navigate("settings") },
                                        contentDescription = "Settings",
                                    )
                                }
                            }
                        },
                    )
                }
            ) { innerPadding ->
                NavHost(navController = navController, startDestination = "transactions") {
                    composable("transactions") {
                        TransactionsScreen(
                            modifier = Modifier.padding(innerPadding),
                        )
                    }
                    composable("settings") {
                        SettingsScreen(
                            modifier = Modifier.padding(innerPadding),
                        )
                    }
                }
            }
        }
    }
}
