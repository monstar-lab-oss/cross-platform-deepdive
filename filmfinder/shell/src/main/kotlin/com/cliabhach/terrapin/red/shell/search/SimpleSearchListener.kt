package com.cliabhach.terrapin.red.shell.search

import com.cliabhach.terrapin.net.Api
import com.cliabhach.terrapin.net.filtered.movie.SearchResultsPage

/**
 *
 */
class SimpleSearchListener(
    internal val api: Api
): SearchListener {

    override suspend fun search(query: CharSequence): SearchResultsPage {
        return if (query.length > 3) {
            api.searchMovies(query.toString())
        } else {
            SearchResultsPage.Unusable("We'll need more than that....")
        }
    }
}
