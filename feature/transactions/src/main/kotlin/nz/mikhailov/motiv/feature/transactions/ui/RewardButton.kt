package nz.mikhailov.motiv.feature.transactions.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nz.mikhailov.motiv.core.common.formatAsCurrency
import nz.mikhailov.motiv.feature.transactions.ui.model.RewardUIO
import nz.mikhailov.motiv.core.design.theme.MotivTheme

@Composable
fun RewardButton(
    modifier: Modifier = Modifier,
    reward: RewardUIO,
    onClick: (RewardUIO) -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = { onClick(reward) },
    ) {
        Icon(reward.icon, contentDescription = null)
        Text(text = "Add ${reward.amount.formatAsCurrency()}", Modifier.padding(start = 8.dp))
    }
}

@Preview
@Composable
fun RewardButtonPreview() {
    MotivTheme {
        Surface {
            RewardButton(
                reward = RewardUIO.Exercise(1.0),
                onClick = {},
            )
        }
    }
}
