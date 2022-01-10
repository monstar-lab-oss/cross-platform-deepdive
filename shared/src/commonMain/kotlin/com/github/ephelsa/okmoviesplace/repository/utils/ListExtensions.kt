package com.github.ephelsa.okmoviesplace.repository.utils

import com.github.ephelsa.okmoviesplace.model.Genre

internal fun List<Genre>.matchGenresIds(ids: List<Int>): List<Genre> {
    return filter { genre ->
        ids.find { it == genre.id } != null
    }
}