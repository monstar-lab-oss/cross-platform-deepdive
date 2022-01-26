package com.github.ephelsa.okmoviesplace.remote.datasource

import com.github.ephelsa.okmoviesplace.remote.json.ActorListJson
import com.github.ephelsa.okmoviesplace.remote.utils.TMDBImagePathProvider
import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal class RemoteActorDataSourceImpl(
    private val client: HttpClient,
    private val imagePath: TMDBImagePathProvider,
) : RemoteActorDataSource {

    override suspend fun actorsPerMovie(movieId: Int, imageWidth: Int): ActorListJson {
        val response = client.get<ActorListJson>("movie/$movieId/credits")

        return response.copy(
            cast = response.cast.map { json ->
                json.withFullPath(imageWidth, imagePath)
            }
        )
    }
}