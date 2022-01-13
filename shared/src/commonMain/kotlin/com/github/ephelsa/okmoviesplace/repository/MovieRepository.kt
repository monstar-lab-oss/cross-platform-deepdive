package com.github.ephelsa.okmoviesplace.repository

import com.github.ephelsa.okmoviesplace.model.Movie
import com.github.ephelsa.okmoviesplace.model.MovieDetails

interface MovieRepository {
    suspend fun upcoming(backdropWidth: Int): List<Movie>
    suspend fun trendingNow(posterWidth: Int): List<Movie>
    suspend fun addFavorite(movieId: Int)
    suspend fun removeFavorite(movieId: Int)
    suspend fun allFavorites(posterWidth: Int): List<MovieDetails>
}