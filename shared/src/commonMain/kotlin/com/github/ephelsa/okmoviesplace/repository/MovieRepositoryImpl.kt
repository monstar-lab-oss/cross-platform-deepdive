package com.github.ephelsa.okmoviesplace.repository

import com.github.ephelsa.okmoviesplace.model.Movie
import com.github.ephelsa.okmoviesplace.model.MovieDetails
import com.github.ephelsa.okmoviesplace.remote.datasource.RemoteMovieDataSource
import com.github.ephelsa.okmoviesplace.repository.utils.matchGenresIds

internal class MovieRepositoryImpl(
    private val remoteMovieDataSource: RemoteMovieDataSource,
    private val genreRepository: GenreRepository,
) : MovieRepository {

    // TODO: This is temporal
    private val favorites = mutableSetOf(460458, 568124, 634649)

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
        favorites.add(movieId)
    }

    override suspend fun removeFavorite(movieId: Int) {
        favorites.remove(movieId)
    }

    override suspend fun allFavorites(posterWidth: Int): List<MovieDetails> {
        val movies = mutableListOf<MovieDetails>()

        favorites.forEach { movieId ->
            val json = remoteMovieDataSource.details(movieId, posterWidth)
            movies.add(json.asModel())
        }

        return movies
    }
}