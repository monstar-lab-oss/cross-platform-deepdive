package com.github.ephelsa.okmoviesplace.remote.json

import com.github.ephelsa.okmoviesplace.model.Genre
import com.github.ephelsa.okmoviesplace.util.ModelMapper
import kotlinx.serialization.Serializable

@Serializable
data class GenreListJson(
    val genres: List<GenreJson>
) : ModelMapper<List<Genre>> {

    @Serializable
    data class GenreJson(
        val id: Int,
        val name: String
    ) : ModelMapper<Genre> {
        override fun asModel() = Genre(id, name)
    }

    override fun asModel(): List<Genre> = genres.map { it.asModel() }
}
