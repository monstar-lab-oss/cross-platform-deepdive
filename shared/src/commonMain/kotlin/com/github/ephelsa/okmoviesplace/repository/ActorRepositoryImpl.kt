package com.github.ephelsa.okmoviesplace.repository

import com.github.ephelsa.okmoviesplace.model.Actor
import com.github.ephelsa.okmoviesplace.remote.datasource.RemoteActorDataSource

internal class ActorRepositoryImpl(
    private val remoteActorDataSource: RemoteActorDataSource,
) : ActorRepository {

    override suspend fun actorsPerMovie(movieId: Int, imageWidth: Int): List<Actor> {
        return remoteActorDataSource.actorsPerMovie(movieId, imageWidth).asModel()
    }
}