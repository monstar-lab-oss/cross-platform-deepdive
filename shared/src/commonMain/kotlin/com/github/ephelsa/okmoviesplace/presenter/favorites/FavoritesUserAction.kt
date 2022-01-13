package com.github.ephelsa.okmoviesplace.presenter.favorites

import com.github.ephelsa.okmoviesplace.model.Genre
import com.github.ephelsa.okmoviesplace.presenter.UserAction

sealed class FavoritesUserAction : UserAction {
    data class LoadPage(
        val imageWidth: Int,
    ) : FavoritesUserAction()

    data class FilterByGenre(
        val genre: Genre,
    ) : FavoritesUserAction()
}