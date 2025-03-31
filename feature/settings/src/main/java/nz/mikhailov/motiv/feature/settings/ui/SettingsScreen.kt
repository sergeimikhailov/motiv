package nz.mikhailov.motiv.feature.settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeveloperMode
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import nz.mikhailov.motiv.core.common.formatAsCurrency
import nz.mikhailov.motiv.core.design.theme.MotivTheme
import nz.mikhailov.motiv.core.design.theme.Typography
import nz.mikhailov.motiv.feature.settings.SettingsViewModel
import nz.mikhailov.motiv.feature.settings.ui.model.RewardUIO

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val rewards by viewModel.rewards.observeAsState(emptyList())
    SettingsScreenLayout(
        modifier = modifier,
        rewards = rewards,
    )
}

@Composable
fun SettingsScreenLayout(
    modifier: Modifier = Modifier,
    rewards: List<RewardUIO> = emptyList(),
) {
    Column(
        modifier = modifier.padding(16.dp),
    ) {
        Text(
            text = "Settings",
            style = Typography.displayLarge,
        )
        Text(
            modifier = Modifier.padding(top = 32.dp),
            text = "Current rewards:",
            style = MaterialTheme.typography.headlineMedium,
        )
        rewards.forEach {
            Reward(
                modifier = Modifier.padding(top = 16.dp),
                reward = it,
            )
        }
        Text(
            modifier = Modifier.padding(top = 32.dp),
            text = "* configuring rewards and their values will come in future versions",
            style = MaterialTheme.typography.labelSmall,
        )
    }
}

@Composable
private fun Reward(
    modifier: Modifier = Modifier,
    reward: RewardUIO,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = reward.icon,
                contentDescription = null, // decorative
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(reward.description)
        }
        Text(reward.amount.formatAsCurrency())
    }
}

@Preview(showSystemUi = true)
@Composable
fun SettingsScreenPreview() {
    MotivTheme {
        Surface {
            SettingsScreenLayout(
                rewards = listOf(
                    RewardUIO(
                        amount = 1.0,
                        icon = Icons.Filled.FitnessCenter,
                        description = "Exercise reward",
                    ),
                    RewardUIO(
                        amount = 2.0,
                        icon = Icons.Filled.School,
                        description = "Study reward",
                    ),
                    RewardUIO(
                        amount = 3.0,
                        icon = Icons.Filled.DeveloperMode,
                        description = "Coding reward",
                    )
                )
            )
        }
    }
}
