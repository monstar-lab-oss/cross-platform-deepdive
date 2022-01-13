package com.github.ephelsa.okmoviesplace.local.datasource

import com.github.ephelsa.okmoviesplace.db.FavoriteQueries
import com.github.ephelsa.okmoviesplace.db.FavoriteTable

internal class LocalMovieDataSourceImpl(
    private val favoriteQueries: FavoriteQueries,
) : LocalMovieDataSource {
    override suspend fun addFavorite(movieId: Int) {
        favoriteQueries.addFavorite(FavoriteTable(movieId.toLong()))
    }

    override suspend fun removeFavorite(movieId: Int) {
        favoriteQueries.removeFavorite(movieId.toLong())
    }

    override suspend fun favorites(): List<Int> {
        return favoriteQueries.favorites().executeAsList()
            .map(Long::toInt)
    }
}