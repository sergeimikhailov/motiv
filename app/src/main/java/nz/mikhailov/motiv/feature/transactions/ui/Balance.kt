package nz.mikhailov.motiv.feature.transactions.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nz.mikhailov.motiv.ui.theme.Typography

@Composable
fun Balance(balance: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
    ) {
        Text("Total is: ", style = Typography.h3)
        Text("$${balance}", style = Typography.h3)
    }
}