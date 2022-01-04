package nz.mikhailov.motiv.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NavigationButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick: () -> Unit,
    contentDescription: String = "",
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(imageVector = icon, contentDescription = contentDescription)
    }
}

@Preview
@Composable
private fun NavigationButtonPreview() {
    NavigationButton(
        icon = Icons.Outlined.Settings,
        onClick = {},
        contentDescription = "Settings",
    )
}