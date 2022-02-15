package com.cliabhach.terrapin.gradle

import org.gradle.api.DefaultTask

/**
 * Parent task for one or more [CopyJsToNodeModules] tasks.
 *
 * @author Philip Cohn-Cort
 */
abstract class ParentCopyJsToNodeModules : DefaultTask() {

    // TODO: Implement configuration methods

    companion object {

        const val COMPILE_JS_TASK_NAME = "compileKotlinJs"

        // TODO: Detect the JS compilation task, somehow
    }
}

