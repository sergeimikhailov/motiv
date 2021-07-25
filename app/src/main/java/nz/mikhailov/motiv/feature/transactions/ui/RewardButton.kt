package nz.mikhailov.motiv.feature.transactions.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun RewardButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick: (Int) -> Unit,
    rewardAmount: Int
) {
    Button(
        modifier = modifier,
        onClick = { onClick(rewardAmount) },
    ) {
        Icon(icon, contentDescription = null)
        Text(text = "Add $${rewardAmount}", Modifier.padding(start = 8.dp))
    }
}