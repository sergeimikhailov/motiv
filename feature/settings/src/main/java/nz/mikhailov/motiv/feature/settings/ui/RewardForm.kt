package nz.mikhailov.motiv.feature.settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeveloperMode
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.NoFood
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RewardForm(
    modifier: Modifier = Modifier,
    initialDescription: String = "",
    initialAmount: String = "",
    initialIcon: IconOption = IconOption.EXERCISE,
    onDescriptionChange: (String) -> Unit,
    onAmountChange: (String) -> Unit,
    onIconChange: (IconOption) -> Unit,
) {
    var description by remember { mutableStateOf(initialDescription) }
    var amount by remember { mutableStateOf(initialAmount) }
    var selectedIcon by remember { mutableStateOf(initialIcon) }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = description,
            onValueChange = { 
                description = it
                onDescriptionChange(it)
            },
            label = { Text("Description") },
        )
        
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = amount,
            onValueChange = { 
                amount = it
                onAmountChange(it)
            },
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
                            onIconChange(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

enum class IconOption(val icon: ImageVector, val label: String) {
    EXERCISE(Icons.Filled.FitnessCenter, "Exercise"),
    STUDY(Icons.Filled.School, "Study"),
    CODE(Icons.Filled.DeveloperMode, "Coding"),
    NO_JUNK_FOOD(Icons.Filled.NoFood, "No Junk Food")
}