package com.github.ephelsa.okmoviesplace.presenter.moviedetails

import com.github.ephelsa.okmoviesplace.presenter.UserAction

sealed class MovieDetailsUserAction : UserAction {
    data class LoadPage(
        val movieId: Int,
        val imageWidth: Int,
        val actorImageWidth: Int
    ) : MovieDetailsUserAction()
}