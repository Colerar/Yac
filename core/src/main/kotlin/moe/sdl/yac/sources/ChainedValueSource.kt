package moe.sdl.yac.sources

import moe.sdl.yac.core.Context
import moe.sdl.yac.parameters.options.Option

/**
 * A [ValueSource] that looks for values in multiple other sources.
 */
class ChainedValueSource(val sources: List<ValueSource>) : ValueSource {
  init {
    require(sources.isNotEmpty()) { "Must provide configuration sources" }
  }

  override fun getValues(context: Context, option: Option): List<ValueSource.Invocation> {
    return sources.asSequence()
      .map { it.getValues(context, option) }
      .firstOrNull { it.isNotEmpty() }
      .orEmpty()
  }
}
