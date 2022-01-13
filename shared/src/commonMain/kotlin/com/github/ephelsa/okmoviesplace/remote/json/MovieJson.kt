package com.github.ephelsa.okmoviesplace.remote.json

import com.github.ephelsa.okmoviesplace.model.ImagePath
import com.github.ephelsa.okmoviesplace.model.ModelMapper
import com.github.ephelsa.okmoviesplace.model.Movie
import com.github.ephelsa.okmoviesplace.remote.utils.ImageFullPath
import com.github.ephelsa.okmoviesplace.remote.utils.TMDBImagePathProvider
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MovieJson(
    val id: Int,
    val adult: Boolean,
    val title: String,
    @SerialName("vote_average")
    val votesAverage: Double,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    @SerialName("poster_path")
    val posterImagePath: String,
    @SerialName("backdrop_path")
    val backdropImagePath: String,
) : ModelMapper<Movie>, ImageFullPath<MovieJson> {

    override fun asModel(): Movie {
        return Movie(
            id = id,
            imagePath = ImagePath(
                backdrop = backdropImagePath,
                poster = posterImagePath
            ),
            title = title,
            isAdult = adult,
            votesAverage = votesAverage,
            genres = emptyList()
        )
    }

    override fun withFullPath(width: Int, provider: TMDBImagePathProvider): MovieJson {
        return copy(
            backdropImagePath = provider.withWidth(backdropImagePath, width),
            posterImagePath = provider.withWidth(posterImagePath, width)
        )
    }
}