package nz.mikhailov.motiv.feature.transactions.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import nz.mikhailov.motiv.feature.transactions.ui.model.Transaction
import nz.mikhailov.motiv.ui.theme.MotivTheme
import java.util.*

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