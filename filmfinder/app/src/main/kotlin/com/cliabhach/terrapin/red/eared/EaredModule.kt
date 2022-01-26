package com.cliabhach.terrapin.red.eared

import com.cliabhach.terrapin.di.HttpClientPlugin
import io.ktor.client.features.cache.*
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Android-specific dependency injection.
 *
 * @author Philip Cohn-Cort
 */
val earedModule = module {
    single(named("API KEY")) {
        BuildConfig.API_KEY
    }
    single {
        listOf<HttpClientPlugin>(
            HttpCache
        )
    }
}