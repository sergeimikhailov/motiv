package nz.mikhailov.motiv.feature.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import nz.mikhailov.motiv.Features
import nz.mikhailov.motiv.feature.settings.ui.model.RewardUIO
import nz.mikhailov.motiv.feature.settings.ui.model.toUIO

@OptIn(ExperimentalCoroutinesApi::class)
class SettingsViewModel(
    private val feature: SettingsFeature = Features.settings,
): ViewModel() {

    val rewards: LiveData<List<RewardUIO>> get() = _rewards
    private val _rewards = MutableLiveData<List<RewardUIO>>()

    init {
        viewModelScope.launch {
            feature.getRewards()
                .mapLatest { it.toUIO() }
                .collect {
                    _rewards.value = it
                }
        }
    }
}
