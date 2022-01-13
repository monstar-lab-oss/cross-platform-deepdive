package com.github.ephelsa.okmoviesplace.presenter.movies

import com.github.ephelsa.okmoviesplace.presenter.UserAction

// Should be one per platform
// ios + android have pull to refresh, but desktop no
sealed class MoviesUserAction : UserAction {
    object PullToRefreshPage : MoviesUserAction()
    data class LoadPage(
        val backdropWidth: Int,
        val posterWidth: Int,
    ) : MoviesUserAction()
}