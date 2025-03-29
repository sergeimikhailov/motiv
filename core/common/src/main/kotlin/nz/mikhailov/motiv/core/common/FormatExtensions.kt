package nz.mikhailov.motiv.core.common

import kotlin.math.abs

fun Double.formatAsCurrency() = "%s$%.2f".format(if (this < 0) "-" else "", abs(this))
