package com.github.ephelsa.okmoviesplace.remote.utils

internal interface ImageFullPath<T> {
    fun withFullPath(width: Int, provider: TMDBImagePathProvider): T
}