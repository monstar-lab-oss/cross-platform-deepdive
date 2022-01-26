package com.github.ephelsa.okmoviesplace.repository

import com.github.ephelsa.okmoviesplace.model.Movie
import com.github.ephelsa.okmoviesplace.model.MovieDetails

interface MovieRepository {
    suspend fun comingSoon(imageWidth: Int): List<Movie>
    suspend fun trendingNow(imageWidth: Int): List<Movie>
    suspend fun addFavorite(movieId: Int)
    suspend fun removeFavorite(movieId: Int)
    suspend fun allFavorites(imageWidth: Int): List<Movie>
    suspend fun isMovieFavorite(movieId: Int): Boolean
    suspend fun details(movieId: Int, imageWidth: Int): MovieDetails
}