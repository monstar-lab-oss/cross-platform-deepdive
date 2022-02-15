package com.cliabhach.terrapin.gradle

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.Input

/**
 * Basic Copy action that understands Kotlin/JS compilation quirks.
 *
 * Copies into a 'node_modules' folder under batagur. Logging may be
 * viewed at [LogLevel.WARN]. See kdoc on [configureAgainstProject] and
 * [actuallyConfigure] for further details.
 *
 * @author Philip Cohn-Cort
 */
abstract class CopyJsToNodeModules : Copy(), Loggable {

    // Should we use that 'Input' annotation instead of method params, perhaps?
    // It seems rather annoying to set up....

    @get:Input
    override val logLevel: LogLevel
        get() = LogLevel.WARN

    /**
     * Public entrypoint to the configuration.
     *
     * Pass along a Kotlin/JS [Project], the name of the root project
     * (this'll be used as a node_modules 'group'), the name of this
     * project (this'll be used as a node_modules subdirectory), and
     * the task that's actually creating the JS files.
     *
     * [outputsJsFiles] should be of type `Kotlin2JsCompile`. Gradle
     * is completely incapable of providing any useful type inference,
     * so you're best off using the source at e.g.
     * [this page on GitHub](https://github.com/JetBrains/kotlin/blob/7952e8b28ffc/libraries/tools/kotlin-gradle-plugin/src/main/kotlin/org/jetbrains/kotlin/gradle/targets/js/ir/KotlinJsIrLink.kt)
     * to understand what kind of properties it has and what your
     * interaction options are. Good luck.
     */
    fun configureAgainstProject(
        project: Project,
        rootProjectName: String,
        projectName: String,
        outputsJsFiles: Task,
    ) {
        val copyTask = this@CopyJsToNodeModules
        project.actuallyConfigure(
            copyTask, rootProjectName, projectName, outputsJsFiles
        )
        description = "Copy $projectName's stuff into a node_modules folder"
        group = "nodejs"
        outputs.upToDateWhen { false }
    }

    /**
     * The real configuration method.
     *
     * This method sets up this CopyJsToNodeModules to copy files from
     * [outputsJsFiles] into a directory called `node_modules`.
     */
    private fun Project.actuallyConfigure(
        copyTask: Copy,
        rootProjectName: String,
        projectName: String,
        outputsJsFiles: Task,
    ) {

        val nodeModulesFolder = rootProject.file(
            "batagur/vendor/node_modules/@$rootProjectName/$projectName/"
        )

        // In the ideal case, these 3 lines would be enough
        copyTask.dependsOn(outputsJsFiles)
        copyTask.from(outputsJsFiles.outputs)
        copyTask.into(nodeModulesFolder)

    }
}