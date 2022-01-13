package com.github.ephelsa.okmoviesplace.di

import com.github.ephelsa.okmoviesplace.coroutines.MainDispatcher
import com.github.ephelsa.okmoviesplace.local.SQLDelightDriverFactory
import com.github.ephelsa.okmoviesplace.presenter.Navigation
import com.github.ephelsa.okmoviesplace.repository.GenreRepository
import com.github.ephelsa.okmoviesplace.repository.MovieRepository
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.direct
import org.kodein.di.instance

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
        bindProvider { Navigation() }
    }

    private val dispatchers = DI.Module("Patform/IOs/Dispatcher") {
        bindProvider(TagsDI.Dispatcher.IO) { MainDispatcher }
    }

    fun provideGenreRepository(): GenreRepository = diContainer.direct.instance()
    fun provideMovieRepository(): MovieRepository = diContainer.direct.instance()
}