package com.github.ephelsa.okmoviesplace.remote

import io.ktor.client.HttpClient
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.parameter
import io.ktor.client.statement.request
import io.ktor.http.URLBuilder
import io.ktor.http.Url
import io.ktor.http.takeFrom
import kotlinx.serialization.json.Json

internal object OKMoviesPlaceClient {
    fun client(baseUrl: Url, language: String, apiKey: String) = HttpClient {
        defaultRequest {
            url.takeFrom(
                URLBuilder().takeFrom(baseUrl).apply {
                    encodedPath += url.encodedPath
                }
            )

            parameter("api_key", apiKey)
            parameter("language", language)
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }

        ResponseObserver { response ->
            println(
                """ 
                * Status: ${response.status.value}
                * Headers: ${response.request.headers}
                * Request: 
                    - URL: ${response.request.url}
                    - Body: ${response.request.content}
                """.trimIndent()
            )
        }
    }
}