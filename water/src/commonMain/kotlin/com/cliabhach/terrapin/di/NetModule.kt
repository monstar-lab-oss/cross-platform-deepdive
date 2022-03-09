package com.cliabhach.terrapin.di

import com.cliabhach.terrapin.net.Api
import com.cliabhach.terrapin.top.BuildKonfig
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import org.koin.core.qualifier.named
import org.koin.dsl.module
import kotlinx.serialization.json.Json as KotlinxJson

/**
 * Based on the guide at [https://insert-koin.io/docs/quickstart/kotlin].
 *
 * @author Philip Cohn-Cort
 */
val netModule = module {
    single(named("API KEY")) {
        BuildKonfig.MDB_API_KEY
    }
    single {
        Api(
            trueApi = get(),
            apiKey = get(qualifier = named("API KEY"))
        )
    }
    single {
        val platformFeatures: List<HttpClientPlugin> = get()
        HttpClient {
            expectSuccess = false

            // Basic logging of requests and responses
            install(Logging) {
                logger = Logger.SIMPLE
            }

            // Serialization and deserialization of Data Classes
            install(JsonFeature) {
                serializer = KotlinxSerializer(json = KotlinxJson {
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            // Additional per-platform features (optional)
            platformFeatures.forEach {
                install(it)
            }
        }
    }
}

typealias HttpClientPlugin = HttpClientFeature<*, *>
