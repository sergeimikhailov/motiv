package nz.mikhailov.motiv.feature.tracker.ui

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import nz.mikhailov.motiv.feature.photo.business.PhotoRepository
import nz.mikhailov.motiv.feature.tracker.business.WeightRepository
import nz.mikhailov.motiv.feature.tracker.business.WeightVisionRepository
import nz.mikhailov.motiv.feature.tracker.ui.DialogState.Error
import nz.mikhailov.motiv.feature.tracker.ui.DialogState.Loading
import nz.mikhailov.motiv.feature.tracker.ui.DialogState.Result
import nz.mikhailov.motiv.util.FirebaseRemoteConfig
import javax.inject.Inject

@HiltViewModel
class TrackerViewModel @Inject constructor(
    private val weightRepository: WeightRepository,
    private val photoRepository: PhotoRepository,
    private val weightVisionRepository: WeightVisionRepository,
    private val config: FirebaseRemoteConfig,
): ViewModel() {

    private val dialogState = MutableStateFlow<DialogState>(Result(""))

    val uiState = combine(
        dialogState,
        weightRepository.latestWeights(),
    ) { weight, latestWeights ->
        TrackerUIO(
            dialogState = weight,
            weightLog = latestWeights.toUIO(),
            autofillEnabled = config.autofillEnabled,
        )
    }.asLiveData()

    fun recordWeight(value: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            weightRepository.recordWeight(value)
            dialogState.value = Result("")
        }
    }

    fun takePictureContract() = photoRepository.takePictureContract()

    fun processPicture(bitmap: Bitmap?) {
        if (bitmap == null) return // TODO
        viewModelScope.launch(Dispatchers.IO) {
            dialogState.value = Loading
            val result = weightVisionRepository.readScale(bitmap)
            val resultState = when {
                result.isSuccess -> Result(result.getOrThrow().toString())
                else -> Error(result.exceptionOrNull()?.message ?: "")
            }
            dialogState.value = resultState
        }
    }

    fun updateDialogState(value: String) = dialogState.tryEmit(Result(value))
}
