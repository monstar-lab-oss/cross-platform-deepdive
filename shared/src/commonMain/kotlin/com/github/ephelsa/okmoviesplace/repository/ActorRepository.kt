package com.github.ephelsa.okmoviesplace.repository

import com.github.ephelsa.okmoviesplace.model.Actor

interface ActorRepository {
    suspend fun actorsPerMovie(movieId: Int, imageWidth: Int): List<Actor>
}