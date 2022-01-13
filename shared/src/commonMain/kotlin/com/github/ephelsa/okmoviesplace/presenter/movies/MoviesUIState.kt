package com.github.ephelsa.okmoviesplace.presenter.movies

import com.github.ephelsa.okmoviesplace.model.Genre
import com.github.ephelsa.okmoviesplace.model.Movie
import com.github.ephelsa.okmoviesplace.presenter.UIState

sealed class MoviesUIState : UIState {
    data class Ready(
        val comingSoonMovies: List<Movie>,
        val movieGenres: List<Genre>,
        val trendingNowMovies: List<Movie>,
    ) : MoviesUIState()

    object Error : MoviesUIState()
    object Loading : MoviesUIState()
    object FirstIn : MoviesUIState()
}