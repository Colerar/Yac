package moe.sdl.yac.parameters.types

import moe.sdl.yac.core.BadParameterValue
import moe.sdl.yac.core.Context
import moe.sdl.yac.parameters.arguments.RawArgument
import moe.sdl.yac.parameters.arguments.convert
import moe.sdl.yac.parameters.options.RawOption
import moe.sdl.yac.parameters.options.convert

internal fun valueToInt(context: Context, it: String): Int {
  return it.toIntOrNull() ?: throw BadParameterValue(context.localization.intConversionError(it))
}

/** Convert the argument values to an `Int` */
fun RawArgument.int() = convert { valueToInt(context, it) }

/** Convert the option values to an `Int` */
fun RawOption.int() = convert({ localization.intMetavar() }) { valueToInt(context, it) }
