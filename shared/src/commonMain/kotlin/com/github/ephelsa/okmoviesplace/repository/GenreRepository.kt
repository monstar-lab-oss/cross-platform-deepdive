package com.github.ephelsa.okmoviesplace.repository

import com.github.ephelsa.okmoviesplace.model.Genre

interface GenreRepository {

    suspend fun reload()

    suspend fun movieById(id: Int): Genre?

    suspend fun allMovieGenres(): List<Genre>
}