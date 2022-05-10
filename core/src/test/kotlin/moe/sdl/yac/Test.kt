package moe.sdl.yac

import kotlinx.coroutines.runBlocking
import moe.sdl.yac.core.CliktCommand
import moe.sdl.yac.parameters.options.option
import moe.sdl.yac.parameters.options.required
import kotlin.test.Test

class Test {
  @Test
  fun test(): Unit = runBlocking {
    val test = object : CliktCommand(
      name = "test"
    ) {
      val test by option().required()

      override suspend fun run() {
        println(test)
      }
    }

    test.main("--test 114514").also {
      println(it)
    }
  }
}
