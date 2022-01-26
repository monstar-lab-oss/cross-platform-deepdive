package com.github.ephelsa.okmoviesplace.presenter.moviedetails

import com.github.ephelsa.okmoviesplace.presenter.UserActionManager
import com.github.ephelsa.okmoviesplace.repository.ActorRepository
import com.github.ephelsa.okmoviesplace.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MovieDetailsUserActionManager(
    dispatcher: CoroutineDispatcher,
    private val moviesRepository: MovieRepository,
    private val actorRepository: ActorRepository,
) : UserActionManager<MovieDetailsUIState, MovieDetailsUserAction>(dispatcher) {

    override fun action(event: MovieDetailsUserAction) {
        when (event) {
            is MovieDetailsUserAction.LoadPage -> loadPage(event)
        }
    }

    private fun loadPage(event: MovieDetailsUserAction.LoadPage) {
        launch {
            state.emit(MovieDetailsUIState.Loading)

            val movieDetails = async { moviesRepository.details(event.movieId, event.imageWidth) }
            val actors = async { actorRepository.actorsPerMovie(event.movieId, event.actorImageWidth) }
            val isMovieFavorite = async { moviesRepository.isMovieFavorite(event.movieId) }

            val ready = MovieDetailsUIState.Ready(
                movieDetails = movieDetails.await(),
                casting = actors.await(),
                isMovieFavorite = isMovieFavorite.await()
            )

            state.emit(ready)
        }
    }

    fun saveFavorite(movieId: Int, onDone: () -> Unit) {
        launch {
            moviesRepository.addFavorite(movieId)
            onDone()
        }
    }

    fun removeFavorite(movieId: Int, onDone: () -> Unit) {
        launch {
            moviesRepository.removeFavorite(movieId)
            onDone()
        }
    }
}