package nz.mikhailov.motiv.feature.transactions.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nz.mikhailov.motiv.ui.theme.MotivTheme

@Composable
fun WithdrawDialog(
    modifier: Modifier = Modifier,
    onConfirm: (Int) -> Unit,
    onCancel: () -> Unit,
) {
    AlertDialog(
        modifier = modifier.fillMaxWidth(),
        onDismissRequest = onCancel,
        title = {
            Text("How much to withdraw?")
        },
        buttons = {
            WithdrawDialogInternalLayout(
                modifier = Modifier.padding(16.dp),
                onConfirm = onConfirm,
                onCancel = onCancel
            )
        }
    )
}

@Composable
private fun WithdrawDialogInternalLayout(
    modifier: Modifier = Modifier,
    onConfirm: (Int) -> Unit,
    onCancel: () -> Unit,
) {
    val (value, setValue) = remember { mutableStateOf("0") }
    Column(
        modifier = modifier,
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .semantics { contentDescription = "Amount to withdraw" },
            value = value,
            onValueChange = setValue,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Number,
            )
        )
        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .semantics { contentDescription = "Confirm withdraw" },
                onClick = { onConfirm(value.toIntOrNull() ?: 0) },
            ) {
                Text(text = "Withdraw")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = onCancel,
                modifier = Modifier.weight(1f),
            ) {
                Text(text = "Cancel")
            }
        }
    }
}

@Preview
@Composable
private fun WithdrawDialogInternalLayoutPreview() {
    MotivTheme {
        Surface {
            WithdrawDialogInternalLayout(
                modifier = Modifier.padding(16.dp),
                onConfirm = {},
                onCancel = {},
            )
        }
    }
}
