package com.cliabhach.terrapin.gradle

import org.gradle.api.Task
import org.gradle.api.logging.LogLevel
import org.gradle.api.logging.Logger

/**
 * Basic abstraction over [Logger.log].
 *
 * @author Philip Cohn-Cort
 */
interface Loggable {

    /**
     * Default logging level.
     */
    val logLevel: LogLevel

    fun Task.logOut(message: String) {
        logger.log(logLevel, message)
    }
}