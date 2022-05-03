package moe.sdl.yac.parameters.types

import moe.sdl.yac.core.BadParameterValue
import moe.sdl.yac.core.Context
import moe.sdl.yac.parameters.arguments.RawArgument
import moe.sdl.yac.parameters.arguments.convert
import moe.sdl.yac.parameters.options.RawOption
import moe.sdl.yac.parameters.options.convert

internal fun valueToLong(context: Context, it: String): Long {
  return it.toLongOrNull() ?: throw BadParameterValue(context.localization.intConversionError(it))
}

/** Convert the argument values to a `Long` */
fun RawArgument.long() = convert { valueToLong(context, it) }

/** Convert the option values to a `Long` */
fun RawOption.long() = convert({ localization.intMetavar() }) { valueToLong(context, it) }
