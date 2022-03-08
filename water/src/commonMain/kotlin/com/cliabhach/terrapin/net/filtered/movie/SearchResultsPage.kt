package com.cliabhach.terrapin.net.filtered.movie

import com.cliabhach.terrapin.net.raw.movie.SearchResult
import kotlin.js.JsExport

/**
 * A frontend-friendly representation of search results.
 *
 * Use [Empty] for the absence of results and for the 'nothing to show'
 * states. Use [Unusable] for error states (no internet, failed auth, that
 * kind of thing). Use [Results] for usable API responses.
 *
 * @author Philip Cohn-Cort
 */
@JsExport
sealed class SearchResultsPage {

    object Empty: SearchResultsPage()

    class Unusable(
        val message: String
    ): SearchResultsPage()

    class Results(
        val items: Array<SearchResult>
    ): SearchResultsPage()
}
