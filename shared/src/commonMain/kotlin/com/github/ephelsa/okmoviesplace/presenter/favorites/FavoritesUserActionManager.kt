package com.github.ephelsa.okmoviesplace.presenter.favorites

import com.github.ephelsa.okmoviesplace.presenter.UserActionManager
import com.github.ephelsa.okmoviesplace.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class FavoritesUserActionManager(
    dispatcher: CoroutineDispatcher,
    private val movieRepository: MovieRepository,
) : UserActionManager<FavoritesUIState, FavoritesUserAction>(dispatcher) {

    override fun action(event: FavoritesUserAction) {
        when (event) {
            is FavoritesUserAction.LoadPage -> loadPage(event)
            is FavoritesUserAction.FilterByGenre -> TODO()
        }
    }

    private fun loadPage(info: FavoritesUserAction.LoadPage) {
        launch {
            state.emit(FavoritesUIState.Loading)

            val movies = movieRepository.allFavorites(info.imageWidth)

            if (movies.isEmpty()) {
                state.emit(FavoritesUIState.Empty)
            } else {
                state.emit(FavoritesUIState.Ready(movies))
            }
        }
    }
}