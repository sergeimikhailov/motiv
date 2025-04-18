package nz.mikhailov.motiv.feature.tracker

import nz.mikhailov.motiv.data.weight.Weight

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