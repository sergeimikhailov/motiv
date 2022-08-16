package nz.mikhailov.motiv.feature.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.mapLatest
import nz.mikhailov.motiv.Features
import nz.mikhailov.motiv.feature.settings.ui.model.RewardUIO
import nz.mikhailov.motiv.feature.settings.ui.model.toUIO

class SettingsViewModel(
    private val feature: SettingsFeature = Features.settings,
): ViewModel() {

    val rewards: LiveData<List<RewardUIO>> =
        feature.getRewards().mapLatest { it.toUIO() }.asLiveData()
}
