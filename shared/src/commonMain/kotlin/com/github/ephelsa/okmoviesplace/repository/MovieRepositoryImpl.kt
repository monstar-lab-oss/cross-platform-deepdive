package com.github.ephelsa.okmoviesplace.repository

import com.github.ephelsa.okmoviesplace.local.datasource.LocalMovieDataSource
import com.github.ephelsa.okmoviesplace.model.Movie
import com.github.ephelsa.okmoviesplace.model.MovieDetails
import com.github.ephelsa.okmoviesplace.remote.datasource.RemoteMovieDataSource
import com.github.ephelsa.okmoviesplace.repository.utils.matchGenresIds

internal class MovieRepositoryImpl(
    private val remoteMovieDataSource: RemoteMovieDataSource,
    private val localMovieDataSource: LocalMovieDataSource,
    private val genreRepository: GenreRepository,
) : MovieRepository {

    override suspend fun comingSoon(imageWidth: Int): List<Movie> {
        val genres = genreRepository.allMovieGenres()
        val upcoming = remoteMovieDataSource.comingSoon(imageWidth)
        val movies = upcoming.map { json ->
            json.asModel()
                .copy(genres = genres.matchGenresIds(json.genreIds))
        }

        return movies
    }

    override suspend fun trendingNow(imageWidth: Int): List<Movie> {
        val genres = genreRepository.allMovieGenres()
        val trending = remoteMovieDataSource.trendingNow(imageWidth)
        val movies = trending.map { movieJson ->
            movieJson.asModel()
                .copy(genres = genres.matchGenresIds(movieJson.genreIds))
        }

        return movies
    }

    override suspend fun addFavorite(movieId: Int) {
        localMovieDataSource.addFavorite(movieId)
    }

    override suspend fun removeFavorite(movieId: Int) {
        localMovieDataSource.removeFavorite(movieId)
    }

    override suspend fun allFavorites(imageWidth: Int): List<MovieDetails> {
        val movieIds = localMovieDataSource.favorites()
        val movies = mutableListOf<MovieDetails>()

        movieIds.forEach { movieId ->
            val json = remoteMovieDataSource.details(movieId, imageWidth)
            movies.add(json.asModel())
        }

        return movies
    }

    override suspend fun details(movieId: Int, imageWidth: Int): MovieDetails {
        return remoteMovieDataSource.details(movieId, imageWidth).asModel()
    }
}