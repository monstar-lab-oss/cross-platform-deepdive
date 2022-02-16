package com.monstarlab.test.moviefinder.kmp.shared.domain

import com.monstarlab.test.moviefinder.kmp.shared.data.*
import com.monstarlab.test.moviefinder.kmp.shared.services.MoviesApi


/**
 * A `use case` to get the currently most popular movies.
 */
class GetPopularMovies(private val moviesApi: MoviesApi) : UseCase<PopularMovies, UseCase.None>() {

    override suspend fun run(params: None): Either<Exception, PopularMovies> {
        return try {
            val movies = moviesApi.getPopularMovies().toModel()
            Success(movies)
        } catch (e: Exception) {
            e.printStackTrace()
            Failure(e)
        }
    }
}