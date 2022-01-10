package com.github.ephelsa.okmoviesplace.remote.datasource

import com.github.ephelsa.okmoviesplace.remote.json.MovieJson
import com.github.ephelsa.okmoviesplace.remote.json.UpcomingJson

internal interface RemoteMovieDataSource {

    suspend fun upcoming(backdropWidth: Int): List<UpcomingJson>

    suspend fun trendingNow(posterWidth: Int): List<MovieJson>
}