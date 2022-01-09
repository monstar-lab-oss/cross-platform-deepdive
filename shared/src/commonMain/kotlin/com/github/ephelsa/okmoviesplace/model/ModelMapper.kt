package com.github.ephelsa.okmoviesplace.model

/**
 * Interface to let any obj be mapped as a model entity
 */
interface ModelMapper<M> {
    fun asModel(): M
}