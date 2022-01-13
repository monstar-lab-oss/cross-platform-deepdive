package com.github.ephelsa.okmoviesplace.presenter.favorites

import com.github.ephelsa.okmoviesplace.presenter.UserAction

sealed class FavoritesUserAction : UserAction {
    data class LoadPage(
        val posterWidth: Int,
    ) : FavoritesUserAction()
}