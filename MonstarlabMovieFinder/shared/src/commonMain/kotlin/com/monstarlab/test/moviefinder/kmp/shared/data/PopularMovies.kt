package com.monstarlab.test.moviefinder.kmp.shared.data

import com.monstarlab.test.moviefinder.kmp.shared.entity.PopularMoviesEntity


data class PopularMovies(
    val page: Int,
    val totalResults: Int,
    val totalPages: Int,
    val results: List<KmmMovie>
)

fun PopularMoviesEntity.toModel() = PopularMovies(
    page = page,
    totalPages = totalPages,
    totalResults = totalResults,
    results = results.map { it.toModel() }
)
