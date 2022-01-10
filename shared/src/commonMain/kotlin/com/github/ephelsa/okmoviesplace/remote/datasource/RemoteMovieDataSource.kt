package com.github.ephelsa.okmoviesplace.remote.datasource

import com.github.ephelsa.okmoviesplace.model.Genre
import com.github.ephelsa.okmoviesplace.model.Movie

internal interface RemoteMovieDataSource {

    suspend fun upcoming(backdropWidth: Int, genresMapper: (List<Int>) -> List<Genre>): List<Movie>

    suspend fun trendingNow(): List<Movie>
}