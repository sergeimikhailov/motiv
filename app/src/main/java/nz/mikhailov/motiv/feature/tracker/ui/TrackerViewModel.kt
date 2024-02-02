package nz.mikhailov.motiv.feature.tracker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import nz.mikhailov.motiv.feature.tracker.business.Weight
import nz.mikhailov.motiv.feature.tracker.business.WeightRepository
import javax.inject.Inject

@HiltViewModel
class TrackerViewModel @Inject constructor(
    private val repository: WeightRepository,
): ViewModel() {

    val uiState = repository.latestWeights()
        .map(List<Weight>::toUIO)
        .map(::TrackerUIO)
        .asLiveData()

    fun recordWeight(value: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.recordWeight(value)
        }
    }
}
