package nz.mikhailov.motiv.feature.transactions.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nz.mikhailov.motiv.core.common.formatAsCurrency
import nz.mikhailov.motiv.core.design.theme.MotivTheme
import nz.mikhailov.motiv.core.design.theme.Typography

@Composable
fun Balance(modifier: Modifier = Modifier, balance: Double) {
    Row(
        modifier = modifier,
    ) {
        Text("Total is: ", style = Typography.displayMedium)
        Text(balance.formatAsCurrency(), style = Typography.displayMedium)
    }
}

@Preview
@Composable
fun BalancePreview() {
    MotivTheme {
        Surface {
            Balance(balance = 10.0)
        }
    }
}
