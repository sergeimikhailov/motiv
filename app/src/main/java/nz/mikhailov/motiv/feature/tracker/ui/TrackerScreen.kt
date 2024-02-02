package nz.mikhailov.motiv.feature.tracker.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import nz.mikhailov.motiv.ui.theme.MotivTheme
import nz.mikhailov.motiv.ui.theme.Typography

@Composable
fun TrackerScreen(
    modifier: Modifier = Modifier,
    viewModel: TrackerViewModel = hiltViewModel(),
) {
    val data by viewModel.uiState.observeAsState(TrackerUIO())
    TrackerScreenLayout(
        modifier = modifier,
        data = data,
        saveNewRecord = {},
    )
}

@Composable
fun TrackerScreenLayout(
    modifier: Modifier = Modifier,
    data: TrackerUIO,
    saveNewRecord: (Double) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }
    Scaffold(
        modifier = modifier.padding(16.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Filled.Edit, contentDescription = "Make a new record")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues),
        ) {
            Text(
                text = "Tracker",
                style = Typography.displayLarge,
            )
            WeightLog(
                modifier = Modifier
                    .fillMaxSize(),
                data = data.weightLog,
            )
        }
    }
    if (showDialog) {
        WeightRecordDialog(
            onSubmit = saveNewRecord,
            onCancel = { showDialog = false }
        )
    }
}

@Preview
@Composable
fun TrackerScreenPreview() {
    MotivTheme {
        Surface {
            TrackerScreenLayout(
                data = TrackerUIO(
                    weightLog = listOf(
                        WeightUIO(date = "13 January 2024", weight = "92.5 kg"),
                        WeightUIO(date = "24 December 2024", weight = "105.0 kg"),
                    )
                ),
                saveNewRecord = {},
            )
        }
    }
}