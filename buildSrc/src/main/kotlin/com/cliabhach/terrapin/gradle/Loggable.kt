package com.cliabhach.terrapin.gradle

import org.gradle.api.Project
import org.gradle.api.logging.LogLevel
import org.gradle.api.logging.Logger

/**
 * Basic abstraction over [Logger.log].
 *
 * @author Philip Cohn-Cort
 */
interface Loggable {

    // TODO: Use in the copy tasks as an input
    val logLevel: LogLevel

    fun logOut(project: Project, message: String) {
        project.logger.log(logLevel, message)
    }
}