package nz.mikhailov.motiv.feature.tracker.ui

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import nz.mikhailov.motiv.feature.photo.business.TakePictureContract
import nz.mikhailov.motiv.feature.tracker.ui.DialogState.Loading
import nz.mikhailov.motiv.feature.tracker.ui.DialogState.Result
import nz.mikhailov.motiv.ui.theme.MotivTheme

@Composable
fun WeightRecordDialog(
    modifier: Modifier = Modifier,
    state: DialogState,
    onValueChange: (String) -> Unit,
    onSubmit: (Double) -> Unit,
    autofillEnabled: Boolean,
    takePictureContract: () -> TakePictureContract,
    onAutofill: (Bitmap?) -> Unit,
    onCancel: () -> Unit,
) {
    val launcher = rememberLauncherForActivityResult(
        contract = takePictureContract(),
        onResult = onAutofill,
    )
    val value = when (state) {
        is Result -> state.weight
        else -> ""
    }
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onCancel,
        title = {
            Text("New weight value")
        },
        confirmButton = {
            Button(
                onClick = { onSubmit(value.toDoubleOrNull() ?: 0.0) },
                enabled = state !is Loading,
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
                onValueChange = onValueChange,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Decimal,
                ),
                enabled = state !is Loading,
                trailingIcon = if (autofillEnabled) {{
                    IconButton(onClick = { launcher.launch(null)} ) {
                        Icon(
                            Icons.Filled.AutoAwesome,
                            contentDescription = "Fill automatically"
                        )
                    }
                }} else null,
            )
        },
    )
}

@Preview
@Composable
private fun WeightRecordDialogPreview() {
    MotivTheme {
        WeightRecordDialog(
            state = Result("76.4"),
            onValueChange = {},
            onSubmit = {},
            autofillEnabled = true,
            onAutofill = {},
            takePictureContract = {
                object : TakePictureContract() {
                    override fun createIntent(context: Context, input: Void?) = Intent()
                    override fun parseResult(resultCode: Int, intent: Intent?) = null
                }
            },
            onCancel = {},
        )
    }
}
