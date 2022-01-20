package com.github.ephelsa.okmoviesplace.di

import com.github.ephelsa.okmoviesplace.local.SQLDelightDriverFactory
import com.github.ephelsa.okmoviesplace.presenter.favorites.FavoritesUserActionManager
import com.github.ephelsa.okmoviesplace.presenter.movies.MoviesUserActionManager
import com.github.ephelsa.okmoviesplace.repository.GenreRepository
import com.github.ephelsa.okmoviesplace.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.direct
import org.kodein.di.instance

@Suppress("UNUSED")
@ThreadLocal
actual object PlatformDI {

    actual val diModule = DI.Module("Platform") {
        import(DataDI.repositoryModule)
        import(PresenterDI.viewManagerModule)
        import(iosModule)
        import(dispatchers)
    }

    private val diContainer: DI by DI.lazy {
        import(diModule)
    }

    private val iosModule = DI.Module("Platform/IOs") {
        bindProvider { SQLDelightDriverFactory() }
    }

    private val dispatchers = DI.Module("Platform/IOs/Dispatcher") {
        bindProvider<CoroutineDispatcher>(TagsDI.Dispatcher.IO) { Dispatchers.Main }
    }

    fun provideGenreRepository(): GenreRepository = diContainer.direct.instance()
    fun provideMovieRepository(): MovieRepository = diContainer.direct.instance()
    fun provideMoviesUserActionManager(): MoviesUserActionManager = diContainer.direct.instance()
    fun provideFavoritesUSerActionManager(): FavoritesUserActionManager = diContainer.direct.instance()
}