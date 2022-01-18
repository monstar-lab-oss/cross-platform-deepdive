package com.cliabhach.terrapin.net.raw.movie

/**
 * @author Philip Cohn-Cort
 */
data class SearchPage(
    val page: Int,
    val results: Array<SearchResult>,
    val totalPages: Int,
    val totalResults: Int,
)