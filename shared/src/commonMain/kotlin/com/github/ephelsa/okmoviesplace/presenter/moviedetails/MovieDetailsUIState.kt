package com.github.ephelsa.okmoviesplace.presenter.moviedetails

import com.github.ephelsa.okmoviesplace.model.Actor
import com.github.ephelsa.okmoviesplace.model.MovieDetails
import com.github.ephelsa.okmoviesplace.presenter.UIState

sealed class MovieDetailsUIState : UIState {
    data class Ready(
        val movieDetails: MovieDetails,
        val casting: List<Actor>,
        val isMovieFavorite: Boolean,
    ) : MovieDetailsUIState()

    object Error : MovieDetailsUIState()
    object Loading : MovieDetailsUIState()
}