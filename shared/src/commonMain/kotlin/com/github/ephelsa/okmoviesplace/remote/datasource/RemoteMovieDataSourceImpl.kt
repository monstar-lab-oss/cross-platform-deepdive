package com.github.ephelsa.okmoviesplace.remote.datasource

import com.github.ephelsa.okmoviesplace.remote.json.MovieDetailsJson
import com.github.ephelsa.okmoviesplace.remote.json.MovieJson
import com.github.ephelsa.okmoviesplace.remote.json.ResultListJson
import com.github.ephelsa.okmoviesplace.remote.utils.TMDBImagePathProvider
import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal class RemoteMovieDataSourceImpl(
    private val client: HttpClient,
    private val imagePath: TMDBImagePathProvider,
) : RemoteMovieDataSource {

    override suspend fun upcoming(backdropWidth: Int): List<MovieJson> {
        return client.get<ResultListJson<MovieJson>>("movie/upcoming")
            .map { json ->
                json.withFullPath(backdropWidth, imagePath)
            }
    }

    override suspend fun trendingNow(posterWidth: Int): List<MovieJson> {
        return client.get<ResultListJson<MovieJson>>("trending/movie/day")
            .map { json ->
                json.withFullPath(posterWidth, imagePath)
            }
    }

    override suspend fun details(movieId: Int, posterWidth: Int): MovieDetailsJson {
        val json = client.get<MovieDetailsJson>("movie/$movieId")

        return json.withFullPath(posterWidth, imagePath)
    }
}