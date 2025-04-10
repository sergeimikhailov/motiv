package nz.mikhailov.motiv.feature.settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
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
import nz.mikhailov.motiv.core.design.theme.MotivTheme
import nz.mikhailov.motiv.core.design.theme.Typography
import nz.mikhailov.motiv.feature.settings.SettingsViewModel
import nz.mikhailov.motiv.feature.settings.ui.model.RewardUIO
import nz.mikhailov.motiv.feature.settings.ui.model.getRewardById

@Composable
fun EditRewardScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
    rewardId: String,
    onBack: () -> Unit,
) {
    val rewards by viewModel.rewards.observeAsState(emptyList())
    val reward = rewards.getRewardById(rewardId)
    
    reward?.let {
        EditRewardContent(
            modifier = modifier,
            initialReward = it,
            onSaveReward = { updatedReward ->
                viewModel.updateReward(updatedReward)
                onBack()
            },
            onDeleteReward = { id ->
                viewModel.deleteReward(id)
                onBack()
            }
        )
    }
}

@Composable
private fun EditRewardContent(
    modifier: Modifier = Modifier,
    initialReward: RewardUIO,
    onSaveReward: (RewardUIO) -> Unit,
    onDeleteReward: (String) -> Unit,
) {
    var description by remember { mutableStateOf(initialReward.description) }
    var amount by remember { mutableStateOf(initialReward.amount.toString()) }
    
    // Find the matching IconOption for the reward's icon
    val initialIcon = IconOption.entries.find { it.icon == initialReward.icon } ?: IconOption.EXERCISE
    var selectedIcon by remember { mutableStateOf(initialIcon) }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Edit reward",
            style = Typography.displayLarge,
        )

        RewardForm(
            initialDescription = description,
            initialAmount = amount,
            initialIcon = selectedIcon,
            onDescriptionChange = { description = it },
            onAmountChange = { amount = it },
            onIconChange = { selectedIcon = it },
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Button(
                onClick = {
                    val amountValue = amount.toDoubleOrNull() ?: 0.0
                    if (description.isNotBlank() && amountValue > 0) {
                        onSaveReward(
                            RewardUIO(
                                id = initialReward.id,
                                amount = amountValue,
                                description = description,
                                icon = selectedIcon.icon
                            )
                        )
                    }
                },
                enabled = description.isNotBlank() && (amount.toDoubleOrNull() ?: 0.0) > 0,
                modifier = Modifier.weight(1f)
            ) {
                Text("Update Reward")
            }
            
            OutlinedButton(
                onClick = { onDeleteReward(initialReward.id) },
                modifier = Modifier.weight(1f)
            ) {
                Text("Delete Reward")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun EditRewardScreenPreview() {
    MotivTheme {
        Surface {
            EditRewardContent(
                modifier = Modifier.padding(16.dp),
                initialReward = RewardUIO(
                    id = "1",
                    amount = 5.0,
                    description = "Exercise for 30min",
                    icon = IconOption.EXERCISE.icon,
                ),
                onSaveReward = {},
                onDeleteReward = {}
            )
        }
    }
}