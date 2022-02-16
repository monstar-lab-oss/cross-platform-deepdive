@file:Suppress("MemberVisibilityCanBePrivate")
package com.monstarlab.test.moviefinder.kmp.shared

import com.monstarlab.test.moviefinder.kmp.shared.repository.MoviesController
import com.monstarlab.test.moviefinder.kmp.shared.domain.GetPopularMovies
import com.monstarlab.test.moviefinder.kmp.shared.repository.PopularMoviesPresenter
import com.monstarlab.test.moviefinder.kmp.shared.services.MoviesApi
import io.ktor.client.engine.HttpClientEngine
import kotlin.native.concurrent.ThreadLocal

/**
 * A basic service locator implementation, as any frameworks like `Kodein` don't really work at the moment.
 */
@ThreadLocal
object ServiceLocator {

    val moviesApi by lazy { MoviesApi(PlatformServiceLocator.httpClientEngine) }

    val getPopularMovies: GetPopularMovies get() = GetPopularMovies(moviesApi)

    val moviesPresenter: PopularMoviesPresenter
        get() = PopularMoviesPresenter(
            getPopularMovies
        )

    val moviesController: MoviesController get() = MoviesController(getPopularMovies)

}

/**
 * Contains some expected dependencies for the [ServiceLocator] that have to be resolved by Android/iOS.
 */
expect object PlatformServiceLocator {
    val httpClientEngine: HttpClientEngine
}
