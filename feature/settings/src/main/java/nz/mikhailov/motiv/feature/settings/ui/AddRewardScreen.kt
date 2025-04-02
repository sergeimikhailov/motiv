package nz.mikhailov.motiv.feature.settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import nz.mikhailov.motiv.core.design.theme.MotivTheme
import nz.mikhailov.motiv.core.design.theme.Typography
import nz.mikhailov.motiv.feature.settings.SettingsViewModel
import nz.mikhailov.motiv.feature.settings.ui.model.RewardUIO

@Composable
fun AddRewardScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
    onSaveReward: (RewardUIO) -> Unit,
) {
    AddRewardContent(
        modifier = modifier,
        onSaveReward = { reward ->
            viewModel.createReward(reward)
            onSaveReward(reward)
        }
    )
}

@Composable
private fun AddRewardContent(
    modifier: Modifier = Modifier,
    onSaveReward: (RewardUIO) -> Unit,
) {
    var description by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var selectedIcon by remember { mutableStateOf(IconOption.EXERCISE) }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Add new reward",
            style = Typography.displayLarge,
        )

        RewardForm(
            onDescriptionChange = { description = it },
            onAmountChange = { amount = it },
            onIconChange = { selectedIcon = it },
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val amountValue = amount.toDoubleOrNull() ?: 0.0
                if (description.isNotBlank() && amountValue > 0) {
                    onSaveReward(
                        RewardUIO(
                            id = "", // Empty ID as this is a new reward
                            amount = amountValue,
                            description = description,
                            icon = selectedIcon.icon
                        )
                    )
                }
            },
            enabled = description.isNotBlank() && (amount.toDoubleOrNull() ?: 0.0) > 0,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Reward")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AddRewardScreenPreview() {
    MotivTheme {
        Surface {
            // In a preview, we don't need the actual viewModel
            AddRewardContent(
                modifier = Modifier.padding(16.dp),
                onSaveReward = {}
            )
        }
    }
}