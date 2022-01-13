package com.github.ephelsa.okmoviesplace.remote.datasource

import com.github.ephelsa.okmoviesplace.remote.json.MovieDetailsJson
import com.github.ephelsa.okmoviesplace.remote.json.MovieJson

internal interface RemoteMovieDataSource {
    suspend fun comingSoon(imageWidth: Int): List<MovieJson>
    suspend fun trendingNow(imageWidth: Int): List<MovieJson>
    suspend fun details(movieId: Int, imageWidth: Int): MovieDetailsJson
}