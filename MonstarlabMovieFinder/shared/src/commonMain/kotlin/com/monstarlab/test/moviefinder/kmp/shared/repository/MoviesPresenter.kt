package com.monstarlab.test.moviefinder.kmp.shared.repository

import com.monstarlab.test.moviefinder.kmp.shared.data.KmmMovie
import com.monstarlab.test.moviefinder.kmp.shared.domain.GetPopularMovies
import com.monstarlab.test.moviefinder.kmp.shared.domain.UseCase
import com.monstarlab.test.moviefinder.kmp.shared.domain.defaultDispatcher
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PopularMoviesPresenter(
    private val getPopularMovies: GetPopularMovies,
    coroutineContext: CoroutineContext = defaultDispatcher
) : BasePresenter<PopularMoviesView>(coroutineContext) {

    override fun onViewAttached(view: PopularMoviesView) {
        view.setLoadingVisible(true)
        getPopularMovies()
    }

    private fun getPopularMovies() {
        scope.launch {
            getPopularMovies(
                UseCase.None,
                onSuccess = { view?.setPopularMovies(it.results) },
                onFailure = { view?.showMoviesFailedToLoad() }
            )
            view?.setLoadingVisible(false)
        }
    }
}

interface PopularMoviesView {

    fun setPopularMovies(movies: List<KmmMovie>)

    fun showMoviesFailedToLoad()

    fun setLoadingVisible(visible: Boolean)
}