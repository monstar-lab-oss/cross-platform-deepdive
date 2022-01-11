package com.github.ephelsa.okmoviesplace.di

import com.github.ephelsa.okmoviesplace.local.SQLDelightDriverFactory
import com.github.ephelsa.okmoviesplace.repository.GenreRepository
import com.github.ephelsa.okmoviesplace.repository.MovieRepository
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.direct
import org.kodein.di.instance

object IOsDI {

    private val kodein by DI.lazy {
        import(iosModule)
        import(CommonDI.repositoryModule)
    }

    private val iosModule = DI.Module("IOsDI") {
        bindProvider { SQLDelightDriverFactory() }
    }

    fun provideGenreRepository(): GenreRepository = kodein.direct.instance()
    fun provideMovieRepository(): MovieRepository = kodein.direct.instance()
}