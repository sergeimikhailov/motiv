package nz.mikhailov.motiv.feature.tracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    TrackerScreenLayout(
        modifier = modifier,
    )
}

@Composable
fun TrackerScreenLayout(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
    ) {
        Text(
            text = "Tracker",
            style = Typography.displayLarge,
        )
    }
}

@Preview
@Composable
fun TrackerScreenPreview() {
    MotivTheme {
        Surface {
            TrackerScreenLayout()
        }
    }
}