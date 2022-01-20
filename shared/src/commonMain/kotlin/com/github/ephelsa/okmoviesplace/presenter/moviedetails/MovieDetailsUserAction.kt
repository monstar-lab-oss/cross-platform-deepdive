package com.github.ephelsa.okmoviesplace.presenter.moviedetails

import com.github.ephelsa.okmoviesplace.presenter.UserAction

sealed class MovieDetailsUserAction : UserAction {
    data class LoadPage(
        val movieId: Int,
        val imageWidth: Int,
    ) : MovieDetailsUserAction()

    data class SaveFavorite(val movieId: Int) : MovieDetailsUserAction()
    data class RemoveFavorite(val movieId: Int) : MovieDetailsUserAction()
}