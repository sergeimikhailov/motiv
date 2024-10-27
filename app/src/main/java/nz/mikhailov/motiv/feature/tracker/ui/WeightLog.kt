package nz.mikhailov.motiv.feature.tracker.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nz.mikhailov.motiv.core.design.theme.MotivTheme

@Composable
fun WeightLog(
    modifier: Modifier = Modifier,
    data: List<WeightUIO>,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(data) {
            WeightLogItem(
                data = it,
            )
        }
    }
}

@Composable
private fun WeightLogItem(
    modifier: Modifier = Modifier,
    data: WeightUIO,
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = data.date)
        Text(text = data.weight)
    }
}

@Preview
@Composable
private fun WeightLogPreview() {
    MotivTheme {
        Surface {
            WeightLog(
                data = listOf(
                    WeightUIO(date = "13 January 2024", weight = "92.5 kg"),
                    WeightUIO(date = "24 December 2024", weight = "105.0 kg"),
                ),
            )
        }
    }
}