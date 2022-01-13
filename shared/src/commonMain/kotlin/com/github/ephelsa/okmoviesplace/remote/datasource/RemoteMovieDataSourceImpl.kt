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

    override suspend fun comingSoon(imageWidth: Int): List<MovieJson> {
        return client.get<ResultListJson<MovieJson>>("movie/upcoming")
            .map { json ->
                json.withFullPath(imageWidth, imagePath)
            }
    }

    override suspend fun trendingNow(imageWidth: Int): List<MovieJson> {
        return client.get<ResultListJson<MovieJson>>("trending/movie/day")
            .map { json ->
                json.withFullPath(imageWidth, imagePath)
            }
    }

    override suspend fun details(movieId: Int, imageWidth: Int): MovieDetailsJson {
        val json = client.get<MovieDetailsJson>("movie/$movieId")

        return json.withFullPath(imageWidth, imagePath)
    }
}