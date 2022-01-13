package com.github.ephelsa.okmoviesplace.di

import com.github.ephelsa.okmoviesplace.di.TagsDI.Presenter
import com.github.ephelsa.okmoviesplace.presenter.favorites.FavoritesUserActionManager
import com.github.ephelsa.okmoviesplace.presenter.movies.MoviesUserActionManager
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

object PresenterDI {

    val viewManagerModule = DI.Module("Presenter/ViewManager") {
        bindProvider(Presenter.Movies) {
            MoviesUserActionManager(instance(TagsDI.Dispatcher.IO), instance(), instance(), instance())
        }

        bindProvider(Presenter.Favorites) {
            FavoritesUserActionManager(instance(TagsDI.Dispatcher.IO), instance(), instance())
        }
    }
}