package com.cliabhach.terrapin.di

import com.cliabhach.terrapin.net.Api
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.logging.*
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Based on the guide at [https://insert-koin.io/docs/quickstart/kotlin].
 *
 * @author Philip Cohn-Cort
 */
val netModule = module {
    single {
        Api(
            trueApi = get(),
            // TODO: Provide API key in this module, with something like https://github.com/Karumi/Hagu
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


            // Additional per-platform features (optional)
            platformFeatures.forEach {
                install(it)
            }
        }
    }
}

typealias HttpClientPlugin = HttpClientFeature<*, *>
