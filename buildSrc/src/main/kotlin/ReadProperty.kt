import java.io.FileInputStream
import java.util.Properties
import org.gradle.api.Project

/**
 * Extension function to read properties in a file or environment.
 *
 * Environment properties are read before any filename.
 *
 * @param prop is the property to return
 * @param filename is the file name placed in the root of the project.
 */
@Suppress("UNCHECKED_CAST")
fun <T> Project.readProperty(prop: String, filename: String): T {
    if (hasProperty(prop)) {
        return properties[prop] as T
    } else {
        val file = rootProject.file(filename)
        val properties = Properties()

        if (file.exists()) {
            properties.load(FileInputStream(file))
        } else {
            throw IllegalArgumentException(
                """
                    Must define Property: $prop in $filename file or project property for build
                """.trimIndent()
            )
        }

        return properties[prop] as T
    }
}
