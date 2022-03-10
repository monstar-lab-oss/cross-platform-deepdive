import csstype.NamedColor
import kotlinx.browser.document
import react.dom.render

/**
 * Main entrypoint to the standalone :grass app.
 *
 * This is not used when another module (like batagur/northern river)
 * includes :grass as a dependency.
 */
fun main() {
    val container = document.createElement("div")
    document.body!!.run {
        this.style.backgroundColor = NamedColor.black.toString()
        appendChild(container)
    }

    val details = createDetails(sampleMovieDetails)
    render(details, container)
}