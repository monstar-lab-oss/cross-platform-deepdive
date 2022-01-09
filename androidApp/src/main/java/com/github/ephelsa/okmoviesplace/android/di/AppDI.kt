package com.github.ephelsa.okmoviesplace.android.di

import com.github.ephelsa.okmoviesplace.android.movies.MoviesViewModel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

internal object AppDI {

    val viewModelModule = DI.Module("Android/ViewModel") {
        bind<MoviesViewModel>() with provider {
            MoviesViewModel(
                genreRepository = instance()
            )
        }
    }
}