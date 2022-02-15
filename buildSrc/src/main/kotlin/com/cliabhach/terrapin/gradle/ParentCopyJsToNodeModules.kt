package com.cliabhach.terrapin.gradle

import org.apache.commons.lang3.StringUtils
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskProvider
import java.util.*

/**
 * Parent task for one or more [CopyJsToNodeModules] tasks.
 *
 * @author Philip Cohn-Cort
 */
abstract class ParentCopyJsToNodeModules : DefaultTask(), Loggable {

    @get:Input
    override val logLevel: LogLevel
        get() = LogLevel.WARN

    /**
     * Define some basic properties for this task.
     *
     * This currently defines [description] and [group], disables the
     * up-to-date check on outputs, and sets up messages to print when
     * the task starts and ends.
     */
    fun configureAgainstProject() {
        actuallyConfigure()
    }

    private fun actuallyConfigure() {
        description = "Copy all the Kotlin/JS output of submodules into our own node_modules folder."
        group = "nodejs"
        outputs.upToDateWhen { false }
        doFirst {
            it.logOut("Preparing JS copy to node_modules...")
        }
        doLast {
            it.logOut("JS copy to node_modules is now complete.")
        }
    }

    private fun Project.registerCopyTask(
        rootProjectName: String,
        projectName: String,
        outputsJsFilesTask: Task
    ): TaskProvider<CopyJsToNodeModules> {
        val capitalizedProjectName = StringUtils.capitalize(projectName)

        return tasks.register(
            "copy${capitalizedProjectName}JsToNodeModules",
            CopyJsToNodeModules::class.java
        ) { copyTask ->

            copyTask.logOut("Now configuring $copyTask")

            copyTask.configureAgainstProject(
                this,
                rootProjectName,
                projectName,
                outputsJsFilesTask,
            )
        }
    }

    companion object {

        const val COMPILE_JS_TASK_NAME = "compileKotlinJs"

        private val baseQualifiers = splitToLowercase(COMPILE_JS_TASK_NAME)

        @JvmStatic
        fun matches(task: Task, vararg variantQualifiers: String): Boolean {
            val taskNameParts = splitToLowercase(task.name)

            val doesMatch = (baseQualifiers + variantQualifiers) == taskNameParts

            // If it's null, this isn't a Kotlin/JS project.
            // Or maybe the API changed since 1.6.20-M1.

            task.logger.log(LogLevel.ERROR, "Task definitely found: $task")

            return doesMatch
        }

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

