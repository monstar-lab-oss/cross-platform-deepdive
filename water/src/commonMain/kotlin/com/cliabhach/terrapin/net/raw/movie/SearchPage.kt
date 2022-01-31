package com.cliabhach.terrapin.net.raw.movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author Philip Cohn-Cort
 */
@Serializable
data class SearchPage(
    val page: Int,
    val results: Array<SearchResult>,
    @SerialName("total_pages")
    val totalPages: Int = 1,
    @SerialName("total_results")
    val totalResults: Int = results.size,
)