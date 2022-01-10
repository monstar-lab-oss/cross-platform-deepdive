package com.github.ephelsa.okmoviesplace.remote.datasource

import com.github.ephelsa.okmoviesplace.remote.json.MovieJson
import com.github.ephelsa.okmoviesplace.remote.json.ResultListJson
import com.github.ephelsa.okmoviesplace.remote.json.UpcomingJson
import com.github.ephelsa.okmoviesplace.remote.utils.TMDBImagePathProvider
import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal class RemoteMovieDataSourceImpl(
    private val client: HttpClient,
    private val imagePath: TMDBImagePathProvider,
) : RemoteMovieDataSource {

    override suspend fun upcoming(backdropWidth: Int): List<UpcomingJson> {
        return client.get<ResultListJson<UpcomingJson>>("movie/upcoming")
            .map { json ->
                json.copy(backdropImagePath = imagePath.withWidth(json.backdropImagePath, backdropWidth))
            }
    }

    override suspend fun trendingNow(posterWidth: Int): List<MovieJson> {
        return client.get<ResultListJson<MovieJson>>("trending/movie/day")
            .map { json ->
                json.copy(posterImagePath = imagePath.withWidth(json.posterImagePath, posterWidth))
            }
    }
}