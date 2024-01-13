package nz.mikhailov.motiv.feature.tracker.business

import java.time.Instant

data class Weight(
    val date: Instant,
    val weight: Double,
)
