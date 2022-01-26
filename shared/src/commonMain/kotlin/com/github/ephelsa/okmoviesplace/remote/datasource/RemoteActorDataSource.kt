package com.github.ephelsa.okmoviesplace.remote.datasource

import com.github.ephelsa.okmoviesplace.remote.json.ActorListJson

internal interface RemoteActorDataSource {
    suspend fun actorsPerMovie(movieId: Int, imageWidth: Int): ActorListJson
}