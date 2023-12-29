package nz.mikhailov.motiv.feature.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.mapLatest
import nz.mikhailov.motiv.feature.settings.ui.model.RewardUIO
import nz.mikhailov.motiv.feature.settings.ui.model.toUIO
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    feature: SettingsFeature,
): ViewModel() {

    val rewards: LiveData<List<RewardUIO>> =
        feature.getRewards().mapLatest { it.toUIO() }.asLiveData()
}
