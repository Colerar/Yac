package moe.sdl.yac.mpp

import java.io.File
import java.text.BreakIterator
import kotlin.system.exitProcess

internal val ANSI_CODE_RE = Regex("${"\u001B"}\\[[^m]*m")

internal fun isWindowsMpp(): Boolean = System.getProperty("os.name")
  .contains(Regex("windows", RegexOption.IGNORE_CASE))

internal val String.graphemeLengthMpp: Int
  get() {
    val breaks = BreakIterator.getCharacterInstance().also { it.setText(replace(ANSI_CODE_RE, "")) }
    return generateSequence { breaks.next() }.takeWhile { it != BreakIterator.DONE }.count()
  }

@Deprecated("Clikt method specified for CLI, should not use in Yac", level = DeprecationLevel.WARNING)
internal fun exitProcessMpp(status: Int) {
  exitProcess(status)
}

internal fun isLetterOrDigit(c: Char): Boolean {
  return c.isLetterOrDigit()
}

internal fun readFileIfExists(filename: String): String? {
  val file = File(filename)
  if (!file.isFile) return null
  return file.bufferedReader().use { it.readText() }
}

internal fun readEnvvar(key: String): String? = System.getenv(key)
