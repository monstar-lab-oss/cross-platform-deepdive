package com.cliabhach.terrapin.red.shell.search

import com.cliabhach.terrapin.net.filtered.movie.SearchResultsPage

/**
 * Contract for searching for things asynchronously.
 */
interface SearchListener {
    /**
     * Search for something using [query].
     */
    suspend fun search(query: CharSequence): SearchResultsPage
}
