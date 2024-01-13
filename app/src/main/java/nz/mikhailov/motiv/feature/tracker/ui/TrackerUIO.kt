package nz.mikhailov.motiv.feature.tracker.ui

import nz.mikhailov.motiv.feature.tracker.business.Weight

data class TrackerUIO(
    val weightLog: List<WeightUIO> = emptyList()
)

internal fun List<Weight>.toUIO() = map(Weight::toUIO)