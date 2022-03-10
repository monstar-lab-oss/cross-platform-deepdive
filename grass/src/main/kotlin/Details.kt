import csstype.FontSize
import csstype.NamedColor
import csstype.px
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.img

/**
 * Properties for a [Details] object.
 *
 * The analogous model that we retrieve from the network is
 * [com.cliabhach.terrapin.net.filtered.movie.MovieDetails].
 */
external interface DetailsExProps: Props {
    var title: String
    var imageUrl: String
    var tagline: String
}

/**
 * Details about a specific movie.
 */
val Details = FC<DetailsExProps> { props ->
    div {
        css {
            padding = 4.px
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
                fontSize = FontSize.medium
            }
            +props.tagline
        }
    }
}