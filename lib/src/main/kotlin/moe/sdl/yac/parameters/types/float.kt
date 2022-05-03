package moe.sdl.yac.parameters.types

import moe.sdl.yac.core.BadParameterValue
import moe.sdl.yac.core.Context
import moe.sdl.yac.parameters.arguments.RawArgument
import moe.sdl.yac.parameters.arguments.convert
import moe.sdl.yac.parameters.options.RawOption
import moe.sdl.yac.parameters.options.convert

private fun valueToFloat(context: Context, it: String): Float {
  return it.toFloatOrNull() ?: throw BadParameterValue(context.localization.floatConversionError(it))
}

/** Convert the argument values to a `Float` */
fun RawArgument.float() = convert { valueToFloat(context, it) }

/** Convert the option values to a `Float` */
fun RawOption.float() = convert({ localization.floatMetavar() }) { valueToFloat(context, it) }
