package com.monstarlab.test.moviefinder.kmp.shared.repository

import com.monstarlab.test.moviefinder.kmp.shared.data.KmmMovie
import com.monstarlab.test.moviefinder.kmp.shared.domain.GetPopularMovies
import com.monstarlab.test.moviefinder.kmp.shared.domain.UseCase
import com.monstarlab.test.moviefinder.kmp.shared.domain.defaultDispatcher
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MoviesController(
    private val getPopularMovies: GetPopularMovies,
    coroutineContext: CoroutineContext = defaultDispatcher
) {
    var listener: MovieResponseListener? = null

    private var scope: PresenterCoroutineScope = PresenterCoroutineScope(coroutineContext)

    fun getPopularMovies() {
        scope.launch {
            getPopularMovies(
                UseCase.None,
                onSuccess = { listener!!.setPopularMovies(it.results) },
                onFailure = { listener!!.showMoviesFailedToLoad() }
            )
            listener!!.setLoadingVisible(false)
        }
    }
}

interface MovieResponseListener {

    fun setPopularMovies(movies: List<KmmMovie>)

    fun showMoviesFailedToLoad()

    fun setLoadingVisible(visible: Boolean)
}