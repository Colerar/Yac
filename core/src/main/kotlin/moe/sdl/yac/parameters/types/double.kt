package moe.sdl.yac.parameters.types

import moe.sdl.yac.core.BadParameterValue
import moe.sdl.yac.core.Context
import moe.sdl.yac.parameters.arguments.RawArgument
import moe.sdl.yac.parameters.arguments.convert
import moe.sdl.yac.parameters.options.RawOption
import moe.sdl.yac.parameters.options.convert

private fun valueToDouble(context: Context, it: String): Double {
  return it.toDoubleOrNull() ?: throw BadParameterValue(context.localization.floatConversionError(it))
}

/** Convert the argument values to a `Double` */
fun RawArgument.double() = convert { valueToDouble(context, it) }

/** Convert the option values to a `Double` */
fun RawOption.double() = convert({ localization.floatMetavar() }) { valueToDouble(context, it) }
