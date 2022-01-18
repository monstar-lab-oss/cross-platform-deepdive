package com.cliabhach.terrapin.di

import com.cliabhach.terrapin.net.Api
import io.ktor.client.*
import org.koin.dsl.module

/**
 * Based on the guide at [https://insert-koin.io/docs/quickstart/kotlin].
 *
 * @author Philip Cohn-Cort
 */
val netModule = module {
    single { Api(trueApi = get()) }
    single {
        HttpClient {
            expectSuccess = false
        }
    }
}