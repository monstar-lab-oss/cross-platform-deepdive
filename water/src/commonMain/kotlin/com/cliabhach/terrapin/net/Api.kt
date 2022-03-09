package com.cliabhach.terrapin.net

import com.cliabhach.terrapin.net.filtered.config.ApiConfig
import com.cliabhach.terrapin.net.filtered.config.ImageUrls
import com.cliabhach.terrapin.net.filtered.movie.MovieDetails
import com.cliabhach.terrapin.net.filtered.movie.SearchResultsPage
import com.cliabhach.terrapin.net.filtered.movie.SearchResultsPage.Empty
import com.cliabhach.terrapin.net.filtered.movie.SearchResultsPage.Results
import com.cliabhach.terrapin.net.filtered.movie.SearchResultsPage.Unusable
import com.cliabhach.terrapin.net.raw.config.Root
import com.cliabhach.terrapin.net.raw.movie.Details
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import com.cliabhach.terrapin.net.raw.movie.SearchPage as RawSearchPage

/**
 * Entrypoint to the network, as essential as water itself.
 *
 * The API key should be provided at build-time using something along the
 * lines of Android's BuildConfig.
 *
 * @author Philip Cohn-Cort
 */
class Api(private val trueApi: HttpClient, private val apiKey: String) {

    suspend fun getMovieDetails(id: Int): MovieDetails {
        val parameters = ParametersBuilder().apply {
            set("api_key", apiKey)
            set("language", "en-US")
        }

        val url = secureUrl(parameters) {
            path("3", "movie", "$id")
        }

        val response = getAsJson(url)

        val config = getImageConfiguration()

        val results: MovieDetails = if (response.status.isSuccess()) {
            val baseUrl = when (config) {
                is ApiConfig.Unknown -> ""
                is ApiConfig.Known -> config.images.getBasePosterUrl(100)
            }

            val details = response.receive<Details>()
            MovieDetails.Result(
                details.id,
                details.title,
                buildImageUrl(baseUrl, details.poster_path),
                details.tagline
            )
        } else {
            val actualStatus = if (response.status.description.isNotBlank()) {
                response.status
            } else {
                HttpStatusCode.fromValue(response.status.value)
            }

            MovieDetails.Unusable(id = id, message = actualStatus.toString())
        }

        return results
    }

    suspend fun searchMovies(query: String): SearchResultsPage {
        val parameters = ParametersBuilder().apply {
            set("query", query)
            set("api_key", apiKey)
            set("language", "en-US")
            set("include_adult", "false")
        }

        val url = secureUrl(parameters) {
            path("3", "search", "movie")
        }

        val response = getAsJson(url)

        val results: SearchResultsPage = if (response.status.isSuccess()) {
            val page = response.receive<RawSearchPage>()
            if (page.results.isEmpty()) {
                Empty
            } else {
                Results(page.results.copyOf())
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

    suspend fun getImageConfiguration(): ApiConfig {
        val parameters = ParametersBuilder().apply {
            set("api_key", apiKey)
        }

        val url = secureUrl(parameters) {
            path("3", "configuration")
        }

        val response = getAsJson(url)

        val results: ApiConfig = if (response.status.isSuccess()) {
            val page = response.receive<Root>()
            ApiConfig.Known(ImageUrls(page.images))
        } else {
            val actualStatus = if (response.status.description.isNotBlank()) {
                response.status
            } else {
                HttpStatusCode.fromValue(response.status.value)
            }

            ApiConfig.Unknown(message = actualStatus.toString())
        }

        return results
    }

    /**
     * Helper function for creating an HTTPS [Url] for The Movie DB.
     */
    private fun secureUrl(
        parameters: ParametersBuilder,
        block: URLBuilder.() -> Unit
    ): Url {
        return URLBuilder(
            protocol = URLProtocol.HTTPS,
            host = "api.themoviedb.org",
            parameters = parameters
        ).apply {
            // Due to https://github.com/ktorio/ktor/issues/1695 the host
            // and protocol constructor params are discarded on Kotlin/JS
            // platforms. Reassert them here just in case.
            //
            // This issue affects ktor 1.6.7
            protocol = URLProtocol.HTTPS
            host = "api.themoviedb.org"
            block()
        }.build()
    }

    private fun buildImageUrl(
        baseUrl: String,
        path: String
    ): String {
        return URLBuilder(baseUrl + path).apply {
            protocol = URLProtocol.HTTPS
            parameters["api_key"] = apiKey
        }.buildString()
    }

    /**
     * Helper function for making a GET request.
     *
     * @see HttpClient.get
     */
    private suspend fun getAsJson(url: Url): HttpResponse {
        val response = trueApi.get<HttpResponse>(
            url = url
        ) {
            accept(ContentType("application", "json"))
        }
        return response
    }
}