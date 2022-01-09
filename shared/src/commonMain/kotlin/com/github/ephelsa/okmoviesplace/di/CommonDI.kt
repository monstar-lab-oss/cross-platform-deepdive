package com.github.ephelsa.okmoviesplace.di

import com.github.ephelsa.okmoviesplace.BuildKonfig
import com.github.ephelsa.okmoviesplace.db.GenreQueries
import com.github.ephelsa.okmoviesplace.db.OKMoviesPlaceDatabase
import com.github.ephelsa.okmoviesplace.local.datasource.LocalGenreDataSource
import com.github.ephelsa.okmoviesplace.local.datasource.LocalGenreDataSourceImpl
import com.github.ephelsa.okmoviesplace.local.SQLDelightDriverFactory
import com.github.ephelsa.okmoviesplace.remote.OKMoviesPlaceClient
import com.github.ephelsa.okmoviesplace.remote.TheMoviesDBUrl
import com.github.ephelsa.okmoviesplace.remote.datasource.RemoteGenreDataSource
import com.github.ephelsa.okmoviesplace.remote.datasource.RemoteGenreDataSourceImpl
import com.github.ephelsa.okmoviesplace.repository.GenreRepository
import com.github.ephelsa.okmoviesplace.repository.GenreRepositoryImpl
import io.ktor.client.HttpClient
import io.ktor.http.Url
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
     * Module for local data sources.
     */
    private val localModule = DI.Module("Common/Local") {
        import(localDatabaseModule)

        bindSingleton<LocalGenreDataSource> { LocalGenreDataSourceImpl(instance()) }
    }

    /**
     * Module for local database.
     */
    private val localDatabaseModule = DI.Module("Common/Local/Database") {
        bind { singleton { OKMoviesPlaceDatabase(instance<SQLDelightDriverFactory>().createDriver()) } }

        bind<GenreQueries>() with provider { instance<OKMoviesPlaceDatabase>().genreQueries }
    }

    /**
     * Module for remote data sources.
     */
    private val remoteModule = DI.Module("Common/Remote") {
        import(remoteClientModule)

        bindSingleton<RemoteGenreDataSource> { RemoteGenreDataSourceImpl(instance()) }
    }

    /**
     * Module for remote client.
     */
    private val remoteClientModule = DI.Module("Common/Remote/Client") {
        bind<Url>("TheMoviesDBUrl") with provider { TheMoviesDBUrl.Url }

        bind<HttpClient>() with provider {
            OKMoviesPlaceClient.client(
                instance("TheMoviesDBUrl"),
                "en-US", // TODO: Replace with real device implementation
                BuildKonfig.theMoviesDBApiKey
            )
        }
    }

    /**
     * Module for repositories.
     */
    val repositoryModule = DI.Module("Common/Repository") {
        importAll(localModule, remoteModule)

        bind<GenreRepository> { provider { GenreRepositoryImpl(instance(), instance()) } }
    }
}