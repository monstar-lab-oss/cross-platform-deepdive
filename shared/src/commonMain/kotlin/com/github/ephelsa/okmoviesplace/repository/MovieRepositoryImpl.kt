package com.github.ephelsa.okmoviesplace.repository

import com.github.ephelsa.okmoviesplace.model.Movie
import com.github.ephelsa.okmoviesplace.remote.datasource.RemoteMovieDataSource
import com.github.ephelsa.okmoviesplace.repository.utils.matchGenresIds

internal class MovieRepositoryImpl(
    private val remoteMovieDataSource: RemoteMovieDataSource,
    private val genreRepository: GenreRepository,
) : MovieRepository {

    override suspend fun upcoming(backdropWidth: Int): List<Movie> {
        val genres = genreRepository.allMovieGenres()
        val upcoming = remoteMovieDataSource.upcoming(backdropWidth)
        val movies = upcoming.map { upcomingJson ->
            upcomingJson.asModel()
                .copy(genres = genres.matchGenresIds(upcomingJson.genreIds))
        }

        return movies
    }

    override suspend fun trendingNow(posterWidth: Int): List<Movie> {
        val genres = genreRepository.allMovieGenres()
        val trending = remoteMovieDataSource.trendingNow(posterWidth)
        val movies = trending.map { movieJson ->
            movieJson.asModel()
                .copy(genres = genres.matchGenresIds(movieJson.genreIds))
        }

        return movies
    }
}