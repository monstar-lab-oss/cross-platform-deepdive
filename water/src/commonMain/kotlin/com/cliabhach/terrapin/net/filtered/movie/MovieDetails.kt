package com.cliabhach.terrapin.net.filtered.movie

/**
 * Filtered counterpart of [com.cliabhach.terrapin.net.raw.movie.Details].
 *
 * @author Philip Cohn-Cort
 */
sealed class MovieDetails(open val id: Int) {

    data class Result(
        override val id: Int,
        val title: String,
        val posterUrl: String,
        val tagline: String,
    ): MovieDetails(id)

    data class Unusable(
        override val id: Int,
        val message: String
    ): MovieDetails(id)
}