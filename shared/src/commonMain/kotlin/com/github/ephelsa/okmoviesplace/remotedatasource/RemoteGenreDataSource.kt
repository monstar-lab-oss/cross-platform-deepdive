package com.github.ephelsa.okmoviesplace.remotedatasource

import com.github.ephelsa.okmoviesplace.model.Genre

interface RemoteGenreDataSource {
    suspend fun movieList(): List<Genre>
}