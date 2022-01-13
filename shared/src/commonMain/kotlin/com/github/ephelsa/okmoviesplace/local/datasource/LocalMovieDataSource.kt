package com.github.ephelsa.okmoviesplace.local.datasource

internal interface LocalMovieDataSource {
    suspend fun addFavorite(movieId: Int)
    suspend fun removeFavorite(movieId: Int)
    suspend fun favorites(): List<Int>
}