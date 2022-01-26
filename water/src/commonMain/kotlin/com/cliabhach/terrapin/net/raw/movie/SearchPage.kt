package com.cliabhach.terrapin.net.raw.movie

import kotlinx.serialization.Serializable

/**
 * @author Philip Cohn-Cort
 */
@Serializable
data class SearchPage(
    val page: Int,
    val results: Array<SearchResult>,
    val totalPages: Int = 1,
    val totalResults: Int = results.size,
)