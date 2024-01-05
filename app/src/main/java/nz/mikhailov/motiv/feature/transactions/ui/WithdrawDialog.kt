package nz.mikhailov.motiv.feature.transactions.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WithdrawDialog(
    modifier: Modifier = Modifier,
    onConfirm: (Int) -> Unit,
    onCancel: () -> Unit,
) {
    val (value, setValue) = remember { mutableStateOf("0") }
    AlertDialog(
        modifier = modifier.fillMaxWidth(),
        onDismissRequest = onCancel,
        title = {
            Text("How much to withdraw?")
        },
        confirmButton = {
            Button(
                modifier = Modifier
                    .semantics { contentDescription = "Confirm withdraw" },
                onClick = { onConfirm(value.toIntOrNull() ?: 0) },
            ) {
                Text(text = "Withdraw")
            }
        },
        dismissButton = {
            Button(
                onClick = onCancel,
                modifier = Modifier,
            ) {
                Text(text = "Cancel")
            }
        },
        text = {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { contentDescription = "Amount to withdraw" },
                value = value,
                onValueChange = setValue,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Number,
                ),
            )
        },
    )
}

@Preview
@Composable
fun WithdrawDialogPreview() {
    WithdrawDialog(
        onConfirm = {},
        onCancel = {},
    )
}