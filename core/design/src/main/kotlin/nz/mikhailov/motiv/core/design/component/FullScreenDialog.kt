package nz.mikhailov.motiv.core.design.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import nz.mikhailov.motiv.core.design.theme.MotivTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullScreenDialog(
    modifier: Modifier = Modifier,
    title: String,
    snackbarHost: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit,
    onDismiss: () -> Unit,
    content: @Composable BoxScope.() -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Scaffold(
            modifier = modifier,
            snackbarHost = snackbarHost,
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(
                            onClick = onDismiss,
                        ) {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = "Close dialog",
                            )
                        }
                    },
                    actions = actions,
                    title = { Text(title) },
                )
            },
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(24.dp)
                    .fillMaxSize(),
                content = content,
            )
        }
    }
}

@Preview
@Composable
private fun FullScreenDialogPreview() {
    MotivTheme {
        FullScreenDialog(
            title = "Fullscreen Dialog Title",
            actions = {
                TextButton(onClick = {}) {
                    Text(text = "Close")
                }
            },
            onDismiss = {},
        ) {
            Text("Fullscreen Dialog Content")
        }
    }
}
