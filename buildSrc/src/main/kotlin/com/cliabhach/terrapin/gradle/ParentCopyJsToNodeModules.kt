package com.cliabhach.terrapin.gradle

import org.apache.commons.lang3.StringUtils
import org.gradle.api.DefaultTask
import java.util.*

/**
 * Parent task for one or more [CopyJsToNodeModules] tasks.
 *
 * @author Philip Cohn-Cort
 */
abstract class ParentCopyJsToNodeModules : DefaultTask() {


    /**
     * Define some basic properties for this task.
     *
     * This currently defines [description] and [group], disables the
     * up-to-date check on outputs, and sets up messages to print when
     * the
     */
    fun configureAgainstProject() {
        actuallyConfigure()
    }

    private fun actuallyConfigure() {
        description = "Copy all the Kotlin/JS output of submodules into our own node_modules folder."
        group = "nodejs"
        outputs.upToDateWhen { false }
        doFirst {
            // TODO: Log start
        }
        doLast {
            // TODO: Log end
        }
    }


    companion object {

        const val COMPILE_JS_TASK_NAME = "compileKotlinJs"

        private val baseQualifiers = splitToLowercase(COMPILE_JS_TASK_NAME)

        // TODO: Detect the JS compilation task, somehow

        /**
         * Quick alias for [StringUtils.splitByCharacterTypeCamelCase].
         *
         * Returned values are all mapped to lowercase under the [Root locale][Locale.ROOT].
         */
        private fun splitToLowercase(text: String): List<String> {
            return StringUtils.splitByCharacterTypeCamelCase(text).map {
                StringUtils.lowerCase(it, Locale.ROOT)
            }
        }
    }
}

