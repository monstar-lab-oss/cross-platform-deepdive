package com.github.ephelsa.okmoviesplace.di

import com.github.ephelsa.okmoviesplace.db.GenreQueries
import com.github.ephelsa.okmoviesplace.db.OKMoviesPlaceDatabase
import com.github.ephelsa.okmoviesplace.localdatasource.LocalGenreDataSource
import com.github.ephelsa.okmoviesplace.localdatasource.LocalGenreDataSourceImpl
import com.github.ephelsa.okmoviesplace.localdatasource.SQLDelightDriverFactory
import com.github.ephelsa.okmoviesplace.remotedatasource.RemoteGenreDataSource
import com.github.ephelsa.okmoviesplace.remotedatasource.RemoteGenreDataSourceImpl
import com.github.ephelsa.okmoviesplace.repository.GenreRepository
import com.github.ephelsa.okmoviesplace.repository.GenreRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton

/**
 * Hold [modules][DI.Module] for common package.
 */
object CommonDI {

    /**
     * Module for local database.
     */
    private val localDatabaseModule = DI.Module("Common/Local/Database") {
        bind { singleton { OKMoviesPlaceDatabase(instance<SQLDelightDriverFactory>().createDriver()) } }

        bind<GenreQueries>() with provider { instance<OKMoviesPlaceDatabase>().genreQueries }
    }

    /**
     * Module for local data sources.
     */
    private val localModule = DI.Module("Common/Local") {
        import(localDatabaseModule)

        bindSingleton<LocalGenreDataSource> { LocalGenreDataSourceImpl(instance()) }
    }

    /**
     * Module for remote data sources.
     */
    private val remoteModule = DI.Module("Common/Remote") {
        bindSingleton<RemoteGenreDataSource>() { RemoteGenreDataSourceImpl() }
    }

    /**
     * Module for repositories.
     */
    val repositoryModule = DI.Module("Common/Repository") {
        importAll(localModule, remoteModule)

        bind<GenreRepository> { provider { GenreRepositoryImpl(instance(), instance()) } }
    }
}