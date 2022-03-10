import com.cliabhach.terrapin.net.filtered.movie.MovieDetails
import csstype.FontSize
import csstype.NamedColor
import csstype.rem
import emotion.react.css
import react.FC
import react.Props
import react.ReactElement
import react.create
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.img

/**
 * Properties for a [Details] object.
 *
 * The analogous model that we retrieve from the network is
 * [MovieDetails].
 */
@JsExport
external interface DetailsExProps: Props {
    var title: String
    var imageUrl: String
    var tagline: String
}

/**
 * Placeholder for when no details are available.
 */
@JsExport
val sampleMovieDetails = MovieDetails.Result(
    id = 1,
    title = "Doesn't This Cat Picture Look Nice?",
    posterUrl = "https://placekitten.com/408/287",
    tagline = "If there were an actual movie object in " +
            "memory, you could read its tagline here."
)

/**
 * Details about a specific movie.
 */
val Details = FC<DetailsExProps> { props ->
    div {
        css {
            padding = 1.rem
            backgroundColor = NamedColor.lightyellow
            color = NamedColor.tomato
        }
        h1 {
            +props.title
        }
        img {
            src = props.imageUrl
        }
        div {
            css {
                paddingTop = 1.rem
                fontSize = FontSize.large
            }
            +props.tagline
        }
    }
}

/**
 * Create a new [Details] component for the given movie.
 *
 * If the parameter here is unusable, we'll render a details
 * screen for [sampleMovieDetails] instead.
 */
@JsExport
fun createDetails(given: MovieDetails): ReactElement<out DetailsExProps> {
    val details: MovieDetails.Result = when (given) {
        is MovieDetails.Unusable -> sampleMovieDetails
        is MovieDetails.Result -> given
    }

    return Details.create {
        title = details.title
        imageUrl = details.posterUrl
        tagline = details.tagline
    }
}