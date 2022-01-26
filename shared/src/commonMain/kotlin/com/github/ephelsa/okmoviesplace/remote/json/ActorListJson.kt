package com.github.ephelsa.okmoviesplace.remote.json

import com.github.ephelsa.okmoviesplace.model.Actor
import com.github.ephelsa.okmoviesplace.model.ImagePath
import com.github.ephelsa.okmoviesplace.model.ModelMapper
import com.github.ephelsa.okmoviesplace.remote.utils.ImageFullPath
import com.github.ephelsa.okmoviesplace.remote.utils.TMDBImagePathProvider
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ActorListJson(
    @SerialName("cast")
    val cast: List<ActorJson>,
) : ModelMapper<List<Actor>> {

    override fun asModel(): List<Actor> {
        return cast.map(ActorJson::asModel)
    }

    @Serializable
    data class ActorJson(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("profile_path")
        val profilePath: String,
    ) : ModelMapper<Actor>, ImageFullPath<ActorJson> {

        override fun asModel(): Actor {
            return Actor(
                id = id,
                name = name,
                imagePath = ImagePath(profilePath, profilePath)
            )
        }

        override fun withFullPath(width: Int, provider: TMDBImagePathProvider): ActorJson {
            return copy(
                profilePath = provider.withWidth(profilePath, width)
            )
        }
    }
}
