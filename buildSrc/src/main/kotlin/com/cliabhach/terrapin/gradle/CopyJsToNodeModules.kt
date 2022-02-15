package com.cliabhach.terrapin.gradle

import org.gradle.api.tasks.Copy

/**
 * Basic Copy action that understands Kotlin/JS compilation quirks.
 *
 * @author Philip Cohn-Cort
 */
abstract class CopyJsToNodeModules : Copy() {

    // Should we use that 'Input' annotation instead of method params, perhaps?
    // It seems rather annoying to set up....

}