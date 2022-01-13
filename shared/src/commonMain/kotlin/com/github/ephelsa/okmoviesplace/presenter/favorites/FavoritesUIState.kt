package com.github.ephelsa.okmoviesplace.presenter.favorites

import com.github.ephelsa.okmoviesplace.model.Genre
import com.github.ephelsa.okmoviesplace.model.MovieDetails
import com.github.ephelsa.okmoviesplace.presenter.UIState

sealed class FavoritesUIState : UIState {
    data class Ready(
        val genres: List<Genre>,
        val movies: List<MovieDetails>,
    ) : FavoritesUIState()

    object Empty : FavoritesUIState()
    object Error : FavoritesUIState()
    object Loading : FavoritesUIState()
}