package com.cliabhach.terrapin.net.raw.movie

/**
 * @author Philip Cohn-Cort
 */
data class SearchResult(
    val id: Int,
    val posterPath: String?,
    val adult: Boolean,
    val overview: String?,
    val releaseDate: String?,
    val genreIds: Array<Int>?,
    val originalTitle: String?,
    val originalLanguage: String?,
    val title: String?,
    val backdropPath: String?,
    val popularity: Double?,
    val voteCount: Int,
    val voteAverage: Double,
    val video: Boolean,

)
