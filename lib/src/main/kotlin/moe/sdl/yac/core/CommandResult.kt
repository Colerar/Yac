package moe.sdl.yac.core

sealed interface CommandResult {
  object Success : CommandResult

  /**
   * An error occurred, with error message and nullable cause
   * @param message message to describe error for debug
   * @param userMessage message to describe error for user, default is the same as [message]
   */
  class Error(message: String?, userMessage: String? = message, cause: Exception? = null) :
    CommandResult, IllegalStateException(message, cause)
}
