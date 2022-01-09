package com.github.ephelsa.okmoviesplace.remote.datasource

import com.github.ephelsa.okmoviesplace.model.Genre

internal interface RemoteGenreDataSource {
    suspend fun movieList(): List<Genre>
}