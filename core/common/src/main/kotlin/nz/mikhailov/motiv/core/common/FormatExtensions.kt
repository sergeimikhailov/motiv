package nz.mikhailov.motiv.core.common

import kotlin.math.abs

fun Int.formatAsCurrency() = "%s$%d".format(if (this < 0) "-" else "", abs(this))
