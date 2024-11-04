package nz.mikhailov.motiv.feature.tracker

import nz.mikhailov.motiv.data.weight.Weight
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

data class WeightUIO(
    val date: String,
    val weight: String,
)

internal fun Weight.toUIO(formatter: DateTimeFormatter = defaultFormatter) = WeightUIO(
    date = formatter.format(date),
    weight = "%.01f".format(weight),
)

private val defaultFormatter: DateTimeFormatter = DateTimeFormatter
    .ofLocalizedDate(FormatStyle.LONG)
    .withZone(ZoneId.systemDefault())