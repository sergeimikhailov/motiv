package nz.mikhailov.motiv.feature.transactions.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import nz.mikhailov.motiv.feature.transactions.ui.model.RewardUIO
import nz.mikhailov.motiv.feature.transactions.ui.model.TransactionUIO
import nz.mikhailov.motiv.feature.transactions.ui.model.TransactionsUIO
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
        onNodeWithContentDescription("Current balance")
            .assertIsDisplayed()
            .onChildren().assertAny(hasText("$", substring = true))
    }

    @Test
    fun shouldAddBalance(): Unit = with(composeTestRule) {
        setContent {
            MotivTheme {
                val initialState = TransactionsUIO(balance = 0, transactions = emptyList())
                val (transactions, setTransactions) = remember { mutableStateOf(initialState) }
                TransactionsScreenLayout(
                    transactions = transactions,
                    addTransaction = {
                        setTransactions(TransactionsUIO(
                            balance = transactions.balance + it.amount,
                            transactions = transactions.transactions
                                    + TransactionUIO(it, ""),
                        ))
                    },
                    withdraw = { },
                )
            }
        }

        onNodeWithContentDescription("Current balance")
            .onChildren().assertAny(hasText("$0"))
        onNodeWithContentDescription("Add study reward")
            .performClick()
        onNodeWithContentDescription("Current balance")
            .onChildren().assertAny(hasText("$2"))
    }

    @Test
    fun shouldWithdrawBalance(): Unit = with(composeTestRule) {
        setContent {
            MotivTheme {
                val initialState = TransactionsUIO(
                    balance = 10,
                    transactions = listOf(TransactionUIO(RewardUIO.Study(10), "")),
                )
                val (transactions, setTransactions) = remember { mutableStateOf(initialState) }
                TransactionsScreenLayout(
                    transactions = transactions,
                    addTransaction = { },
                    withdraw = {
                        setTransactions(TransactionsUIO(
                            balance = transactions.balance - it,
                            transactions = transactions.transactions
                                    + TransactionUIO(RewardUIO.Unknown(-it), ""),
                        ))
                    },
                )
            }
        }

        onNodeWithContentDescription("Current balance")
            .onChildren().assertAny(hasText("$10"))
        onNodeWithContentDescription("Withdraw")
            .performClick()
        onNodeWithContentDescription("Amount to withdraw")
            .performTextReplacement("7")
        onNodeWithContentDescription("Confirm withdraw")
            .performClick()
        onNodeWithContentDescription("Current balance")
            .onChildren().assertAny(hasText("$3"))
    }
}