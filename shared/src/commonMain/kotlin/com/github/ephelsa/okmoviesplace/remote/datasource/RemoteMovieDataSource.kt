package com.github.ephelsa.okmoviesplace.remote.datasource

import com.github.ephelsa.okmoviesplace.remote.json.MovieDetailsJson
import com.github.ephelsa.okmoviesplace.remote.json.MovieJson

internal interface RemoteMovieDataSource {
    suspend fun upcoming(backdropWidth: Int): List<MovieJson>
    suspend fun trendingNow(posterWidth: Int): List<MovieJson>
    suspend fun details(movieId: Int, posterWidth: Int): MovieDetailsJson
}