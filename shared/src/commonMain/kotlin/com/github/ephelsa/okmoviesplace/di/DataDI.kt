package com.github.ephelsa.okmoviesplace.di

import com.github.ephelsa.okmoviesplace.BuildKonfig
import com.github.ephelsa.okmoviesplace.db.FavoriteQueries
import com.github.ephelsa.okmoviesplace.db.GenreQueries
import com.github.ephelsa.okmoviesplace.db.OKMoviesPlaceDatabase
import com.github.ephelsa.okmoviesplace.local.SQLDelightDriverFactory
import com.github.ephelsa.okmoviesplace.local.datasource.LocalGenreDataSource
import com.github.ephelsa.okmoviesplace.local.datasource.LocalGenreDataSourceImpl
import com.github.ephelsa.okmoviesplace.local.datasource.LocalMovieDataSource
import com.github.ephelsa.okmoviesplace.local.datasource.LocalMovieDataSourceImpl
import com.github.ephelsa.okmoviesplace.remote.TMDBClient
import com.github.ephelsa.okmoviesplace.remote.datasource.RemoteActorDataSource
import com.github.ephelsa.okmoviesplace.remote.datasource.RemoteActorDataSourceImpl
import com.github.ephelsa.okmoviesplace.remote.datasource.RemoteGenreDataSource
import com.github.ephelsa.okmoviesplace.remote.datasource.RemoteGenreDataSourceImpl
import com.github.ephelsa.okmoviesplace.remote.datasource.RemoteMovieDataSource
import com.github.ephelsa.okmoviesplace.remote.datasource.RemoteMovieDataSourceImpl
import com.github.ephelsa.okmoviesplace.remote.utils.TMDBImagePathProvider
import com.github.ephelsa.okmoviesplace.repository.ActorRepository
import com.github.ephelsa.okmoviesplace.repository.ActorRepositoryImpl
import com.github.ephelsa.okmoviesplace.repository.GenreRepository
import com.github.ephelsa.okmoviesplace.repository.GenreRepositoryImpl
import com.github.ephelsa.okmoviesplace.repository.MovieRepository
import com.github.ephelsa.okmoviesplace.repository.MovieRepositoryImpl
import io.ktor.client.HttpClient
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton

/**
 * Hold [modules][DI.Module] for common package.
 */
object DataDI {

    /**
     * Module for local data sources.
     */
    private val localModule = DI.Module("Common/Local") {
        import(localDatabaseModule)

        bindSingleton<LocalGenreDataSource> { LocalGenreDataSourceImpl(instance()) }
        bindSingleton<LocalMovieDataSource> { LocalMovieDataSourceImpl(instance()) }
    }

    /**
     * Module for local database.
     */
    private val localDatabaseModule = DI.Module("Common/Local/Database") {
        bind<OKMoviesPlaceDatabase>() with singleton { OKMoviesPlaceDatabase(instance<SQLDelightDriverFactory>().createDriver()) }
        bind<GenreQueries>() with provider { instance<OKMoviesPlaceDatabase>().genreQueries }
        bind<FavoriteQueries>() with provider { instance<OKMoviesPlaceDatabase>().favoriteQueries }
    }

    /**
     * Module for remote data sources.
     */
    private val remoteModule = DI.Module("Common/Remote") {
        import(remoteClientModule)

        bindSingleton<RemoteGenreDataSource> { RemoteGenreDataSourceImpl(instance()) }
        bindSingleton<RemoteMovieDataSource> { RemoteMovieDataSourceImpl(instance(), instance()) }
        bindSingleton<RemoteActorDataSource> { RemoteActorDataSourceImpl(instance(), instance()) }
    }

    /**
     * Module for remote client.
     */
    private val remoteClientModule = DI.Module("Common/Remote/Client") {
        bind<HttpClient>() with provider {
            TMDBClient.client(
                "en-US", // TODO: Replace with real device implementation
                BuildKonfig.theMovieDBApiKey
            )
        }
    }

    /**
     * Module to provide any utils
     */
    private val utilsModule = DI.Module("Common/Utils") {
        bindProvider { TMDBImagePathProvider() }
    }

    /**
     * Module for repositories.
     */
    val repositoryModule = DI.Module("Common/Repository") {
        importAll(localModule, remoteModule, utilsModule)

        bindSingleton<GenreRepository> { GenreRepositoryImpl(instance(), instance()) }
        bindSingleton<MovieRepository> { MovieRepositoryImpl(instance(), instance(), instance()) }
        bindSingleton<ActorRepository> { ActorRepositoryImpl(instance()) }
    }
}