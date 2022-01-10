package com.github.ephelsa.okmoviesplace.repository

import com.github.ephelsa.okmoviesplace.model.Movie

interface MovieRepository {
    suspend fun upcoming(backdropWidth: Int): List<Movie>

    suspend fun trendingNow(): List<Movie>
}