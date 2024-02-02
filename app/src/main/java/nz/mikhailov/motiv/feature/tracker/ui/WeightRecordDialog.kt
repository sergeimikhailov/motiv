package nz.mikhailov.motiv.feature.tracker.ui

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import nz.mikhailov.motiv.ui.theme.MotivTheme

@Composable
fun WeightRecordDialog(
    modifier: Modifier = Modifier,
    onSubmit: (Double) -> Unit,
    onCancel: () -> Unit,
) {
    val (value, setValue) = remember { mutableStateOf("") }
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onCancel,
        title = {
            Text("New weight value")
        },
        confirmButton = {
            Button(
                onClick = { onSubmit(value.toDoubleOrNull() ?: 0.0) },
            ) {
                Text(text = "Save")
            }
        },
        dismissButton = {
            Button(onClick = onCancel) {
                Text(text = "Cancel")
            }
        },
        text = {
            TextField(
                value = value,
                onValueChange = setValue,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Decimal,
                ),
            )
        },
    )
}

@Preview
@Composable
private fun WeightRecordDialogPreview() {
    MotivTheme {
        WeightRecordDialog(
            onSubmit = {},
            onCancel = {},
        )
    }
}
