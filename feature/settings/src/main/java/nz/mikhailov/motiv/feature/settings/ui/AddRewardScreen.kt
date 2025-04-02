package nz.mikhailov.motiv.feature.settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeveloperMode
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.NoFood
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddRewardContent(
    modifier: Modifier = Modifier,
    onSaveReward: (RewardUIO) -> Unit,
) {
    var description by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var selectedIcon by remember { mutableStateOf(IconOption.EXERCISE) }
    var expanded by remember { mutableStateOf(false) }

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

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
        )
        
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        )

        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                value = selectedIcon.label,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { TrailingIcon(expanded = expanded) },
                leadingIcon = { Icon(imageVector = selectedIcon.icon, contentDescription = null) },
                label = { Text("Icon") }
            )
            
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                IconOption.entries.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option.label) },
                        leadingIcon = { Icon(imageVector = option.icon, contentDescription = null) },
                        onClick = {
                            selectedIcon = option
                            expanded = false
                        }
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val amountValue = amount.toDoubleOrNull() ?: 0.0
                if (description.isNotBlank() && amountValue > 0) {
                    onSaveReward(
                        RewardUIO(
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

private enum class IconOption(val icon: ImageVector, val label: String) {
    EXERCISE(Icons.Filled.FitnessCenter, "Exercise"),
    STUDY(Icons.Filled.School, "Study"),
    CODE(Icons.Filled.DeveloperMode, "Coding"),
    NO_JUNK_FOOD(Icons.Filled.NoFood, "No Junk Food")
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