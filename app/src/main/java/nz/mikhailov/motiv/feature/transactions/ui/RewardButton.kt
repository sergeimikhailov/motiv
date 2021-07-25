package nz.mikhailov.motiv.feature.transactions.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nz.mikhailov.motiv.ui.theme.MotivTheme

@Composable
fun RewardButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick: (Int) -> Unit,
    rewardAmount: Int,
) {
    Button(
        modifier = modifier,
        onClick = { onClick(rewardAmount) },
    ) {
        Icon(icon, contentDescription = null)
        Text(text = "Add $${rewardAmount}", Modifier.padding(start = 8.dp))
    }
}

@Preview
@Composable
fun RewardButtonPreview() {
    MotivTheme {
        Surface {
            RewardButton(
                icon = Icons.Filled.FitnessCenter,
                onClick = {},
                rewardAmount = 1,
            )
        }
    }
}
