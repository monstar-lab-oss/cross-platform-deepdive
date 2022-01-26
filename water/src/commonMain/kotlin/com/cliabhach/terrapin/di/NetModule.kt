package com.cliabhach.terrapin.di

import com.cliabhach.terrapin.net.Api
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.logging.*
import org.koin.dsl.module

/**
 * Based on the guide at [https://insert-koin.io/docs/quickstart/kotlin].
 *
 * @author Philip Cohn-Cort
 */
val netModule = module {
    single { Api(trueApi = get()) }
    single {
        val platformFeatures: List<HttpClientPlugin> = get()
        HttpClient {
            expectSuccess = false
            install(Logging) {
                logger = Logger.SIMPLE
            }
            platformFeatures.forEach {
                install(it)
            }
        }
    }
}

typealias HttpClientPlugin = HttpClientFeature<*,*>
