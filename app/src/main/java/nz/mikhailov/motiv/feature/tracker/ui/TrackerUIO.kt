package nz.mikhailov.motiv.feature.tracker.ui

import nz.mikhailov.motiv.feature.tracker.business.Weight

data class TrackerUIO(
    val dialogState: DialogState = DialogState.Result(""),
    val weightLog: List<WeightUIO> = emptyList(),
    val autofillEnabled: Boolean = false,
)

sealed interface DialogState {
    data object Loading : DialogState

    data class Result(
        val weight: String,
    ) : DialogState

    data class Error(
        val reason: String,
    ) : DialogState
}

internal fun List<Weight>.toUIO() = map(Weight::toUIO)