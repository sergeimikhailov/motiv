package nz.mikhailov.motiv.feature.tracker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import nz.mikhailov.motiv.feature.tracker.business.Weight
import nz.mikhailov.motiv.feature.tracker.business.WeightRepository
import javax.inject.Inject

@HiltViewModel
class TrackerViewModel @Inject constructor(
    repository: WeightRepository,
): ViewModel() {

    val uiState = repository.latestWeights()
        .map(List<Weight>::toUIO)
        .map(::TrackerUIO)
        .asLiveData()
}
