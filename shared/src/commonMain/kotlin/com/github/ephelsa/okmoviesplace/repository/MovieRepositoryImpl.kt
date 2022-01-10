package com.github.ephelsa.okmoviesplace.repository

import com.github.ephelsa.okmoviesplace.model.Genre
import com.github.ephelsa.okmoviesplace.model.Movie
import com.github.ephelsa.okmoviesplace.remote.datasource.RemoteMovieDataSource

internal class MovieRepositoryImpl(
    private val remoteMovieDataSource: RemoteMovieDataSource,
    private val genreRepository: GenreRepository,
) : MovieRepository {

    override suspend fun upcoming(backdropWidth: Int): List<Movie> {
        val genres = genreRepository.allMovieGenres()
        val upcoming = remoteMovieDataSource.upcoming(backdropWidth) {
            genres.matchGenresIds(it)
        }

        println("Upcoming: $upcoming")

        return upcoming
    }

    private fun List<Genre>.matchGenresIds(ids: List<Int>): List<Genre> {
        return filter { genre ->
            ids.find { it == genre.id } != null
        }
    }

    override suspend fun trendingNow(): List<Movie> {
        TODO("Not yet implemented")
    }
}