package com.github.ephelsa.okmoviesplace.presenter.favorites

import com.github.ephelsa.okmoviesplace.model.Genre
import com.github.ephelsa.okmoviesplace.model.MovieDetails
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
            val genres: List<Genre> = movies.map(MovieDetails::genres).flatten()

            if (movies.isEmpty()) {
                state.emit(FavoritesUIState.Empty)
            } else {
                state.emit(FavoritesUIState.Ready(genres, movies))
            }
        }
    }
}