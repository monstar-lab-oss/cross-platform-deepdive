package com.monstarlab.test.moviefinder.kmp.shared

import io.ktor.client.engine.*
import io.ktor.client.engine.ios.Ios
import kotlin.native.concurrent.ThreadLocal

/**
 * Contains some expected dependencies for the [ServiceLocator] that have to be resolved by Android/iOS.
 */
@ThreadLocal
actual object PlatformServiceLocator {

    actual val httpClientEngine: HttpClientEngine by lazy { Ios.create() }
}