package com.monstarlab.test.moviefinder.kmp.shared.data

import com.monstarlab.test.moviefinder.kmp.shared.entity.MovieEntity
import kotlinx.serialization.Serializable

private const val MOVIE_POSTER_BASE_URL = "https://image.tmdb.org/t/p/original"

data class KmmMovie(
    val popularity: Double,
    val id: Int,
    val video: Boolean,
    val voteCount: Int,
    val voteAverage: Double,
    val title: String,
    val originalLanguage: String,
    val originalTitle: String,
    val genreIds: List<Int>,
    val backdropPath: String,
    val adult: Boolean,
    val overview: String,
    val posterPath: String,
    var favorite:Boolean = false
) {

    val completePosterPath = MOVIE_POSTER_BASE_URL + posterPath
}

fun MovieEntity.toModel() = KmmMovie(
    popularity = popularity,
    id = id,
    video = video,
    voteCount = voteCount,
    voteAverage = voteAverage,
    title = title,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    genreIds = genreIds,
    backdropPath = backdropPath,
    adult = adult,
    overview = overview,
    posterPath = posterPath
)