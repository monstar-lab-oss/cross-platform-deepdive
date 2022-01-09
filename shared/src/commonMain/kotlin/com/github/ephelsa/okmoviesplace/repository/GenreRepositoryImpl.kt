package com.github.ephelsa.okmoviesplace.repository

import com.github.ephelsa.okmoviesplace.local.datasource.LocalGenreDataSource
import com.github.ephelsa.okmoviesplace.model.Genre
import com.github.ephelsa.okmoviesplace.remote.datasource.RemoteGenreDataSource

internal class GenreRepositoryImpl(
    private val remoteGenreDataSource: RemoteGenreDataSource,
    private val localGenreDataSource: LocalGenreDataSource
) : GenreRepository {

    override suspend fun reload() {
        refreshGenresLocally()
    }

    override suspend fun movieById(id: Int): Genre? {
        return if (localGenreDataSource.isMovieListEmpty()) {
            refreshGenresLocally().find { it.id == id }
        } else {
            localGenreDataSource.movieById(id) ?: refreshGenresLocally().find { it.id == id }
        }
    }

    override suspend fun allMovieGenres(): List<Genre> {
        return if (localGenreDataSource.isMovieListEmpty()) {
            refreshGenresLocally()
        } else {
            localGenreDataSource.allMovieGenres()
        }
    }

    private suspend fun refreshGenresLocally(): List<Genre> {
        val remoteResult = remoteGenreDataSource.movieList()
        localGenreDataSource.storeMovieList(remoteResult)

        return remoteResult
    }
}