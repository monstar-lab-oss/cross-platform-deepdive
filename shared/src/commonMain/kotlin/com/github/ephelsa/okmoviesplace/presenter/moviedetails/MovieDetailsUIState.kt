package com.github.ephelsa.okmoviesplace.presenter.moviedetails

import com.github.ephelsa.okmoviesplace.model.MovieDetails
import com.github.ephelsa.okmoviesplace.presenter.UIState

sealed class MovieDetailsUIState : UIState {
    data class Ready(
        val movieDetails: MovieDetails,
    ) : MovieDetailsUIState()

    object OperationDone : MovieDetailsUIState()
    object Error : MovieDetailsUIState()
    object Loading : MovieDetailsUIState()
}