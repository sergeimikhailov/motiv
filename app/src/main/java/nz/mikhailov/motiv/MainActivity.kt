package nz.mikhailov.motiv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import nz.mikhailov.motiv.core.design.theme.MotivTheme
import nz.mikhailov.motiv.feature.settings.ui.AddRewardScreen
import nz.mikhailov.motiv.feature.settings.ui.EditRewardScreen
import nz.mikhailov.motiv.feature.settings.ui.SettingsScreen
import nz.mikhailov.motiv.feature.tracker.TrackerScreen
import nz.mikhailov.motiv.feature.transactions.ui.TransactionsScreen
import nz.mikhailov.motiv.ui.NavigationButton

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
                    TopAppBar(
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
                        navigationIcon = {
                            val isTransactionsScreen = "transactions" == navBackStackEntry?.destination?.route
                            if (!isTransactionsScreen) {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowBack,
                                        contentDescription = "Navigate back"
                                    )
                                }
                            }
                        }
                    )
                },
                bottomBar = {
                    NavigationBar {
                        NavigationBarItem(
                            selected = "transactions" == navBackStackEntry?.destination?.route,
                            onClick = { navController.navigate("transactions") },
                            icon = { Icon(Icons.Filled.EmojiEvents, contentDescription = "Rewards") },
                            label = { Text(text = "Rewards") },
                        )
                        NavigationBarItem(
                            selected = "tracker" == navBackStackEntry?.destination?.route,
                            onClick = { navController.navigate("tracker") },
                            icon = { Icon(Icons.Filled.FiberManualRecord, contentDescription = "Tracker") },
                            label = { Text(text = "Tracker") },
                        )
                    }
                },
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
                            onNavigateToAddReward = {
                                navController.navigate("settings/add_reward")
                            },
                            onNavigateToEditReward = { reward ->
                                navController.navigate("settings/edit_reward/${reward.id}")
                            },
                        )
                    }
                    composable("settings/add_reward") {
                        AddRewardScreen(
                            modifier = Modifier.padding(innerPadding),
                            onSaveReward = { _ ->
                                // Reward is saved in the AddRewardScreen
                                navController.popBackStack()
                            }
                        )
                    }
                    composable(
                        route = "settings/edit_reward/{rewardId}",
                        arguments = listOf(
                            navArgument("rewardId") {
                                type = NavType.StringType
                            }
                        )
                    ) { backStackEntry ->
                        val rewardId = backStackEntry.arguments?.getString("rewardId") ?: ""
                        
                        EditRewardScreen(
                            modifier = Modifier.padding(innerPadding),
                            rewardId = rewardId,
                            onBack = { navController.popBackStack() },
                        )
                    }
                    composable("tracker") {
                        TrackerScreen(
                            modifier = Modifier.padding(innerPadding),
                        )
                    }
                }
            }
        }
    }
}
