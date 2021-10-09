package nz.mikhailov.motiv.feature.transactions.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import nz.mikhailov.motiv.ui.theme.MotivTheme
import org.junit.Rule
import org.junit.Test

class TransactionsScreensComposeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun shouldDisplayBalanceOnStart(): Unit = with(composeTestRule) {
        setContent {
            MotivTheme {
                TransactionsScreen()
            }
        }
        onNodeWithContentDescription("Current balance").assertIsDisplayed()
    }
}