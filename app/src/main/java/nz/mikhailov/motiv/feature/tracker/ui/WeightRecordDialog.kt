package nz.mikhailov.motiv.feature.tracker.ui

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import nz.mikhailov.motiv.feature.photo.business.TakePictureContract
import nz.mikhailov.motiv.feature.tracker.ui.DialogState.Loading
import nz.mikhailov.motiv.feature.tracker.ui.DialogState.Result
import nz.mikhailov.motiv.ui.dialog.FullScreenDialog
import nz.mikhailov.motiv.ui.theme.MotivTheme

@Composable
fun WeightRecordDialog(
    modifier: Modifier = Modifier,
    state: DialogState,
    onValueChange: (String) -> Unit,
    onSubmit: () -> Unit,
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
    val focusRequester = remember { FocusRequester() }
    val snackbarHostState = remember { SnackbarHostState() }
    val currentLaunchAutofill = remember { { launcher.launch(null) } }
    FullScreenDialog(
        modifier = modifier,
        onDismiss = onCancel,
        title = "New weight value",
        snackbarHost = { SnackbarHost(snackbarHostState) },
        actions = {
            TextButton(
                onClick = onSubmit,
                enabled = state !is Loading,
            ) {
                Text(text = "Save")
            }
        },
        content = {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                value = value,
                maxLines = 1,
                minLines = 1,
                onValueChange = onValueChange,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Decimal,
                ),
                enabled = state !is Loading,
                trailingIcon = if (autofillEnabled) {{
                    IconButton(onClick = currentLaunchAutofill) {
                        Icon(
                            Icons.Filled.AutoAwesome,
                            contentDescription = "Fill automatically"
                        )
                    }
                }} else null,
            )
        },
    )
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    if (state is DialogState.Error) {
        LaunchedEffect(snackbarHostState) {
            snackbarHostState.showSnackbar(state.reason, duration = SnackbarDuration.Long)
        }
    }
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
