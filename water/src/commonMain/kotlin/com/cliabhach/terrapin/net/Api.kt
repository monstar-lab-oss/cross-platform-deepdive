package com.cliabhach.terrapin.net

import com.cliabhach.terrapin.net.filtered.movie.SearchResultsPage
import com.cliabhach.terrapin.net.filtered.movie.SearchResultsPage.Empty
import com.cliabhach.terrapin.net.filtered.movie.SearchResultsPage.Results
import com.cliabhach.terrapin.net.filtered.movie.SearchResultsPage.Unusable
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import com.cliabhach.terrapin.net.raw.movie.SearchPage as RawSearchPage

/**
 * Entrypoint to the network, as essential as water itself.
 *
 * @author Philip Cohn-Cort
 */
class Api(private val trueApi: HttpClient) {

    suspend fun searchMovies(query: String): SearchResultsPage {
        val response = trueApi.get<HttpResponse>(
            "https://api.themoviedb.org/3/search/movie/?query=$query&api_key="
        )

        val results: SearchResultsPage = if (response.status.isSuccess()) {
            val page = response.receive<RawSearchPage>()
            if (page.results.isEmpty()) {
                Empty
            } else {
                Results(page.results.toList())
            }
        } else {
            val actualStatus = if (response.status.description.isNotBlank()) {
                response.status
            } else {
                HttpStatusCode.fromValue(response.status.value)
            }

            Unusable(message = actualStatus.toString())
        }

        return results
    }
}