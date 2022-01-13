package com.github.ephelsa.okmoviesplace.presenter.favorites

import com.github.ephelsa.okmoviesplace.model.MovieDetails
import com.github.ephelsa.okmoviesplace.presenter.UIState

sealed class FavoritesUIState : UIState {
    data class Ready(
        val favorites: List<MovieDetails>,
    ) : FavoritesUIState()

    object Error : FavoritesUIState()
    object Loading : FavoritesUIState()
}