package com.github.ephelsa.okmoviesplace.remote.datasource

import com.github.ephelsa.okmoviesplace.model.Genre
import com.github.ephelsa.okmoviesplace.model.Movie
import com.github.ephelsa.okmoviesplace.remote.utils.TMDBImagePathProvider
import com.github.ephelsa.okmoviesplace.remote.json.MovieJson
import com.github.ephelsa.okmoviesplace.remote.json.ResultListJson
import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal class RemoteMovieDataSourceImpl(
    private val client: HttpClient,
    private val imagePath: TMDBImagePathProvider,
) : RemoteMovieDataSource {

    override suspend fun upcoming(backdropWidth: Int, genresMapper: (List<Int>) -> List<Genre>): List<Movie> {
        return client.get<ResultListJson<MovieJson>>("movie/upcoming")
            .map { json ->
                json.asModel()
                    .copy(
                        imagePath = imagePath.withWidth(json.backdropImagePath, backdropWidth),
                        genres = genresMapper(json.genreIds)
                    )
            }
    }

    override suspend fun trendingNow(): List<Movie> {
        TODO("Not yet implemented")
    }
}