import csstype.NamedColor
import kotlinx.browser.document
import react.dom.render

fun main() {
    val container = document.createElement("div")
    document.body!!.run {
        this.style.backgroundColor = NamedColor.black.toString()
        appendChild(container)
    }

    val details = createDetails(sampleMovieDetails)
    render(details, container)
}