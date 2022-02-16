package com.monstarlab.test.moviefinder.kmp.shared.services

import com.monstarlab.test.moviefinder.kmp.shared.entity.PopularMoviesEntity
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private const val BASE_URL = "api.themoviedb.org/3"
private const val HEADER_AUTHORIZATION = "Authorization"
private const val API_KEY = "4cb1eeab94f45affe2536f2c684a5c9e"

@Suppress("EXPERIMENTAL_API_USAGE")
class MoviesApi(clientEngine: HttpClientEngine) {

    private val client = HttpClient(clientEngine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }


    suspend fun getPopularMovies(): PopularMoviesEntity {
        // Actually we're able to just return the get()-call and Ktor's JsonFeature will automatically do the
        // JSON parsing for us. However, this currently doesn't work with Kotlin/Native as it doesn't support reflection
        // and we have to manually use PopularMoviesEntity.serializer()
        val response = client.get<HttpResponse> {
            url {
                protocol = URLProtocol.HTTP
                host = BASE_URL
                encodedPath = "discover/movie"
                parameter("api_key", API_KEY)
                parameter("sort_by", "popularity.desc")
               // header(HEADER_AUTHORIZATION, API_KEY.asBearerToken())
            }
        }

        val jsonBody = response.readText()
        return Json.decodeFromString(jsonBody)
        /* PopularMoviesEntity(
             page = 0,
             results = listOf(),
             totalPages = 0,
             totalResults = 0
         )*/
        //Json.parse(PopularMoviesEntity.serializer(), jsonBody)
    }
}

private fun String.asBearerToken() = "Bearer $this"