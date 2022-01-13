package com.github.ephelsa.okmoviesplace.presenter.favorites

import com.github.ephelsa.okmoviesplace.presenter.Navigation
import com.github.ephelsa.okmoviesplace.presenter.UserActionManager
import com.github.ephelsa.okmoviesplace.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class FavoritesUserActionManager(
    dispatcher: CoroutineDispatcher,
    navigation: Navigation,
    private val movieRepository: MovieRepository,
) : UserActionManager<FavoritesUIState, FavoritesUserAction>(dispatcher, navigation) {

    override fun runEvent(event: FavoritesUserAction) {
        when (event) {
            is FavoritesUserAction.LoadPage -> loadPage(event)
        }
    }

    private fun loadPage(info: FavoritesUserAction.LoadPage) {
        launch {
            state.emit(FavoritesUIState.Loading)
            state.emit(FavoritesUIState.Ready(movieRepository.allFavorites(info.posterWidth)))
        }
    }
}