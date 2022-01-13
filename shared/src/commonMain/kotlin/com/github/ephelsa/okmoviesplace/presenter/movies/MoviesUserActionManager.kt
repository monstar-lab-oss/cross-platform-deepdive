package com.github.ephelsa.okmoviesplace.presenter.movies

import com.github.ephelsa.okmoviesplace.presenter.Navigation
import com.github.ephelsa.okmoviesplace.presenter.UserActionManager
import com.github.ephelsa.okmoviesplace.repository.GenreRepository
import com.github.ephelsa.okmoviesplace.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MoviesUserActionManager(
    dispatcher: CoroutineDispatcher,
    navigation: Navigation,
    private val movieRepository: MovieRepository,
    private val genreRepository: GenreRepository,
) : UserActionManager<MoviesUIState, MoviesUserAction>(dispatcher, navigation) {

    override fun runEvent(event: MoviesUserAction) {
        when (event) {
            is MoviesUserAction.LoadPage -> loadPage(event)
            MoviesUserAction.PullToRefreshPage -> TODO()
        }
    }

    private fun loadPage(event: MoviesUserAction.LoadPage) {
        launch {
            state.emit(MoviesUIState.Loading)

            val comingSoonMovies = async { movieRepository.comingSoon(event.comingSoonWidth) }
            val movieGenres = async { genreRepository.allMovieGenres() }
            val trendingNowMovies = async { movieRepository.trendingNow(event.trendingNowWidth) }

            // At the moment to implement Result, here should handle things...
            val resourceReady = MoviesUIState.Ready(
                comingSoonMovies.await(),
                movieGenres.await(),
                trendingNowMovies.await()
            )

            state.emit(resourceReady)
        }
    }
}