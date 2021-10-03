package nz.mikhailov.motiv.feature.transactions.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nz.mikhailov.motiv.ui.theme.MotivTheme
import nz.mikhailov.motiv.ui.theme.Typography
import nz.mikhailov.motiv.util.formatAsCurrency

@Composable
fun Balance(modifier: Modifier = Modifier, balance: Int) {
    Row(
        modifier = modifier,
    ) {
        Text("Total is: ", style = Typography.h3)
        Text(balance.formatAsCurrency(), style = Typography.h3)
    }
}

@Preview
@Composable
fun BalancePreview() {
    MotivTheme {
        Surface {
            Balance(balance = 10)
        }
    }
}
