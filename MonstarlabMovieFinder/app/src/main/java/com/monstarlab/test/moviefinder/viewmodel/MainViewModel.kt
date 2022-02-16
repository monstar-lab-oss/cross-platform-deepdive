package com.monstarlab.test.moviefinder.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.monstarlab.test.moviefinder.kmp.shared.data.KmmMovie
import com.monstarlab.test.moviefinder.kmp.shared.data.SortType
import com.monstarlab.test.moviefinder.repository.MovieRepository
import java.io.IOException

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val mContext: Application = getApplication()
    private val repository = MovieRepository()
    var pageIndex = MutableLiveData<Int>()
    var sort = MutableLiveData<SortType>()
    var totalPages: LiveData<Int> = repository.getTotalPages()
    var totalResults: LiveData<Int> = repository.getTotalResults()
    var movies: LiveData<List<KmmMovie>> = repository.getMovies()

    init {
        pageIndex.value = 0
        sort.value = SortType.POPULAR
    }

    fun startMovieSearch(word: String, page: Int) {
        try {
            repository.fetchSearchMovie(word, page)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun startPopularMovieSearch(page: Int) {
        try {
            repository.fetchPopularMovies(page)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun startNewestMovieSearch(page: Int) {
        try {
            repository.fetchNewestMovies(page)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun startUpcomingMovieSearch(page: Int) {
        try {
            repository.fetchUpcomingMovies(page)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun startTopRatedMovieSearch(page: Int) {
        try {
            repository.fetchTopRatedMovies(page)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun startNowPlayingMovieSearch(page: Int) {
        try {
            repository.fetchNowPlayingMovies(page)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun startFavoriteMovieSearch() {
        repository.loadFavoriteMovies(mContext)
    }


}