package moe.sdl.yac.core

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class ParseStringToArgs {
  @Test
  fun parseSimple() {
    assertContentEquals(
      listOf("string", "to", "114514"),
      "string to 114514".parseToArgs()
    )
  }

  @Test
  fun parseEscape() {
    assertContentEquals(
      listOf("string", "to 114514"),
      """string to\ 114514""".parseToArgs()
    )
  }

  @Test
  fun parseEmptyQuote() {
    assertContentEquals(
      listOf("string", "to 114514", ""),
      """string to\ 114514 """"".parseToArgs()
    )
  }

  @Test
  fun parseEscapeInsideQuote() {
    assertContentEquals(
      listOf("1", "1", """1\"1"""),
      """1 1 "1\"1"""".parseToArgs()
    )
  }
}
