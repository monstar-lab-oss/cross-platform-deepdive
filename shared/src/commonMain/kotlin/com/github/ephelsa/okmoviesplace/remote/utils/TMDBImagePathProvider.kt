package com.github.ephelsa.okmoviesplace.remote.utils

import com.github.ephelsa.okmoviesplace.remote.TMDBConstants

internal class TMDBImagePathProvider {

    fun fullSize(name: String): String {
        return "${TMDBConstants.BASE_IMAGE_PATH}/original/$name"
    }

    fun withWidth(name: String, width: Int): String {
        return "${TMDBConstants.BASE_IMAGE_PATH}/w$width$name"
    }
}