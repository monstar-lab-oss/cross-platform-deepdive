package com.github.ephelsa.okmoviesplace.repository

import com.github.ephelsa.okmoviesplace.localdatasource.LocalGenreDataSource
import com.github.ephelsa.okmoviesplace.model.Genre
import com.github.ephelsa.okmoviesplace.remotedatasource.RemoteGenreDataSource

class GenreRepositoryImpl(
    private val remoteGenreDataSource: RemoteGenreDataSource,
    private val localGenreDataSource: LocalGenreDataSource
) : GenreRepository {

    override suspend fun movieById(id: Int): Genre? {
        return if (localGenreDataSource.isMovieListEmpty()) {
            refreshGenresLocally().find { it.id == id }
        } else {
            localGenreDataSource.movieById(id) ?: refreshGenresLocally().find { it.id == id }
        }
    }

    private suspend fun refreshGenresLocally(): List<Genre> {
        val remoteResult = remoteGenreDataSource.movieList()
        localGenreDataSource.storeMovieList(remoteResult)

        return remoteResult
    }
}