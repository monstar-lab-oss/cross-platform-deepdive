package com.github.ephelsa.okmoviesplace.remote.json

import com.github.ephelsa.okmoviesplace.model.ModelMapper
import kotlinx.serialization.Serializable

@Serializable
internal data class ResultListJson<T>(
    val page: Int,
    val results: List<T>,
) {
    fun <M> map(block: (T) -> M): List<M> {
        return results.map(block)
    }
}

internal fun <T> ResultListJson<ModelMapper<T>>.asModel(): List<T> {
    return this.results.map(ModelMapper<T>::asModel)
}
