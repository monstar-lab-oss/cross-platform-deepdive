package com.github.ephelsa.okmoviesplace.presenter.moviedetails

import com.github.ephelsa.okmoviesplace.presenter.UserActionManager
import com.github.ephelsa.okmoviesplace.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MovieDetailsUserActionManager(
    dispatcher: CoroutineDispatcher,
    private val moviesRepository: MovieRepository,
) : UserActionManager<MovieDetailsUIState, MovieDetailsUserAction>(dispatcher) {

    override fun action(event: MovieDetailsUserAction) {
        when (event) {
            is MovieDetailsUserAction.LoadPage -> loadPage(event)
            is MovieDetailsUserAction.RemoveFavorite -> removeFavorite(event)
            is MovieDetailsUserAction.SaveFavorite -> saveFavorite(event)
        }
    }

    private fun loadPage(event: MovieDetailsUserAction.LoadPage) {
        launch {
            state.emit(MovieDetailsUIState.Loading)

            val movieDetails = moviesRepository.details(event.movieId, event.imageWidth)

            state.emit(MovieDetailsUIState.Ready(movieDetails))
        }
    }

    private fun saveFavorite(event: MovieDetailsUserAction.SaveFavorite) {
        launch {
            state.emit(MovieDetailsUIState.Loading)
            moviesRepository.addFavorite(event.movieId)
            state.emit(MovieDetailsUIState.OperationDone)
        }
    }

    private fun removeFavorite(event: MovieDetailsUserAction.RemoveFavorite) {
        launch {
            state.emit(MovieDetailsUIState.Loading)
            moviesRepository.removeFavorite(event.movieId)
            state.emit(MovieDetailsUIState.OperationDone)
        }
    }
}