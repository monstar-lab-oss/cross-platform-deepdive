package com.github.ephelsa.okmoviesplace.remote.datasource

import com.github.ephelsa.okmoviesplace.model.Genre
import com.github.ephelsa.okmoviesplace.remote.json.GenreListJson
import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal class RemoteGenreDataSourceImpl(
    private val client: HttpClient
) : RemoteGenreDataSource {

    override suspend fun movieList(): List<Genre> {
        return client.get<GenreListJson>("genre/movie/list").asModel()
    }
}