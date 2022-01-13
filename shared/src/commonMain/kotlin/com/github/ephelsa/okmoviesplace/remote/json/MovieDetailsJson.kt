package com.github.ephelsa.okmoviesplace.remote.json

import com.github.ephelsa.okmoviesplace.model.ImagePath
import com.github.ephelsa.okmoviesplace.model.ModelMapper
import com.github.ephelsa.okmoviesplace.model.MovieDetails
import com.github.ephelsa.okmoviesplace.remote.utils.ImageFullPath
import com.github.ephelsa.okmoviesplace.remote.utils.TMDBImagePathProvider
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MovieDetailsJson(
    val id: Int,
    @SerialName("backdrop_path")
    val backdropPath: String,
    @SerialName("poster_path")
    val posterPath: String,
    val title: String,
    @SerialName("adult")
    val isAdult: Boolean,
    @SerialName("vote_average")
    val votesAverage: Double,
    @SerialName("genres")
    val genres: List<GenreListJson.GenreJson>,
    @SerialName("overview")
    val description: String,
    @SerialName("original_language")
    val language: String,
) : ModelMapper<MovieDetails>, ImageFullPath<MovieDetailsJson> {

    override fun asModel(): MovieDetails {
        return MovieDetails(
            id = id,
            imagePath = ImagePath(
                backdrop = backdropPath,
                poster = posterPath
            ),
            title = title,
            isAdult = isAdult,
            votesAverage = votesAverage,
            genres = genres.map(GenreListJson.GenreJson::asModel),
            description = description,
            language = language
        )
    }

    override fun withFullPath(width: Int, provider: TMDBImagePathProvider): MovieDetailsJson {
        return copy(
            backdropPath = provider.withWidth(backdropPath, width),
            posterPath = provider.withWidth(posterPath, width)
        )
    }
}
