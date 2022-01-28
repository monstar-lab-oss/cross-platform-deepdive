package com.cliabhach.terrapin.net.raw.movie

import kotlinx.serialization.Serializable

/**
 * Raw counterpart of [com.cliabhach.terrapin.net.filtered.movie.MovieDetails].
 *
 * @author Philip Cohn-Cort
 */
@Serializable
data class Details(
    val id: Int,
    val title: String,
    val poster_path: String,
    val tagline: String
)