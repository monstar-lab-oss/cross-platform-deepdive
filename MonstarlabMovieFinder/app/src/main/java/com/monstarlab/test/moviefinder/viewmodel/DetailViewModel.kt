package com.monstarlab.test.moviefinder.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.monstarlab.test.moviefinder.db.entity.Movie
import com.monstarlab.test.moviefinder.kmp.shared.data.KmmMovie
import com.monstarlab.test.moviefinder.repository.MovieRepository

class DetailViewModel(private val mApplication: Application) : AndroidViewModel(
    mApplication
) {
    private val repository = MovieRepository()

    @JvmField
    var isFavorite = MutableLiveData<Boolean>()
    fun loadFavoriteMovie(id: Int) {
        val favorite = repository.loadFavoriteMovie(mApplication.applicationContext, id)
        isFavorite.postValue(favorite)
    }

    fun saveFavoriteMovie(movie: KmmMovie) {
        if (movie.favorite) {
            repository.insertFavorite(mApplication.applicationContext, movie)
        } else {
            repository.deleteFavorite(mApplication.applicationContext, movie)
        }
    }

    init {
        isFavorite.value = false
    }
}