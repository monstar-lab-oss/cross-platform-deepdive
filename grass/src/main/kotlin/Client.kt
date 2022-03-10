import com.cliabhach.terrapin.net.filtered.movie.MovieDetails
import kotlinx.browser.document
import react.create
import react.dom.render

fun main() {
    val container = document.createElement("div")
    document.body!!.appendChild(container)

    val sample = MovieDetails.Result(
        id = 1,
        title = "Doesn't This Cat Picture Look Nice?",
        posterUrl = "https://placekitten.com/408/287",
        tagline = "If there were an actual movie object in " +
                "memory, you could read its tagline here."
    )

    val welcome = Welcome.create {
        name = "Kotlin/JS"
    }
    val details = Details.create {
        title = sample.title
        imageUrl = sample.posterUrl
        tagline = sample.tagline
    }
    render(welcome, container)
}