package com.github.ephelsa.okmoviesplace.di

import com.github.ephelsa.okmoviesplace.presenter.favorites.FavoritesUserActionManager
import com.github.ephelsa.okmoviesplace.presenter.moviedetails.MovieDetailsUserActionManager
import com.github.ephelsa.okmoviesplace.presenter.movies.MoviesUserActionManager
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

object PresenterDI {

    val viewManagerModule = DI.Module("Presenter/ViewManager") {
        bindProvider {
            MoviesUserActionManager(instance(TagsDI.Dispatcher.IO), instance(), instance())
        }

        bindProvider {
            FavoritesUserActionManager(instance(TagsDI.Dispatcher.IO), instance())
        }

        bindProvider {
            MovieDetailsUserActionManager(instance(TagsDI.Dispatcher.IO), instance(), instance())
        }
    }
}