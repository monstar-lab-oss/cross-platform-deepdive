package com.monstarlab.test.moviefinder.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.monstarlab.test.moviefinder.db.AppDatabase
import com.monstarlab.test.moviefinder.kmp.shared.ServiceLocator
import com.monstarlab.test.moviefinder.kmp.shared.data.KmmMovie
import com.monstarlab.test.moviefinder.kmp.shared.repository.MovieResponseListener

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.*


class MovieRepository {
    private val movies = MutableLiveData<List<KmmMovie>>()
    private val page = MutableLiveData<Int>()
    private val totalPages = MutableLiveData<Int>()
    private val totalResults = MutableLiveData<Int>()
    private val gson = Gson()

    private val moviesController by lazy { ServiceLocator.moviesController }

    init {
        movies.value = ArrayList()
        page.value = 0
        totalPages.value = 0
    }

    fun getPage(): LiveData<Int> {
        return page
    }

    fun getTotalPages(): LiveData<Int> {
        return totalPages
    }

    fun getTotalResults(): LiveData<Int> {
        return totalResults
    }

    fun getMovies(): LiveData<List<KmmMovie>> {
        return movies
    }

    fun loadFavoriteMovie(context: Context, id: Int): Boolean {
        val movie = AppDatabase.getInstance(context)!!.movieDao().getFavoriteMovieById(id)
        return movie?.favorite ?: false
    }

    fun insertFavorite(context: Context, movie: KmmMovie) {
        //  AppDatabase.getInstance(context)!!.movieDao().insertMovie(movie)
    }

    fun deleteFavorite(context: Context, movie: KmmMovie) {
        // AppDatabase.getInstance(context)!!.movieDao().deleteMovie(movie)
    }

    fun loadFavoriteMovies(context: Context) {
/*        val movieEntityList = AppDatabase.getInstance(context)!!.movieDao().all
        movies.postValue(movieEntityList)*/
    }

    @Throws(IOException::class)
    fun fetchSearchMovie(query: String, pageNavigation: Int) {
        /* val dataReceived: Response<MovieResponse> =
             movieService.searchMovieRequest(query, pageNavigation, API_KEY).execute()
         buildMoviesResponse(dataReceived, pageNavigation)*/
    }

    @Throws(IOException::class)
    fun fetchPopularMovies(pageNavigation: Int) {
        /* val dataReceived: Response<MovieResponse> =
             movieService.popularMovieRequest(pageNavigation, API_KEY)
                 .execute()
         buildMoviesResponse(dataReceived, pageNavigation)*/
        moviesController.getPopularMovies()
        moviesController.listener = object : MovieResponseListener {
            override fun setPopularMovies(movieResponseList: List<KmmMovie>) {
                movies.postValue(movieResponseList)
            }

            override fun showMoviesFailedToLoad() {
                //TODO("Not yet implemented")
            }

            override fun setLoadingVisible(visible: Boolean) {
                //TODO("Not yet implemented")
            }

        }
    }

    @Throws(IOException::class)
    fun fetchNewestMovies(pageNavigation: Int) {
        /* val dataReceived: Response<MovieResponse> =
             movieService.newestMovieRequest(pageNavigation, API_KEY)
                 .execute()
         buildMoviesResponse(dataReceived, pageNavigation)*/
    }

    @Throws(IOException::class)
    fun fetchTopRatedMovies(pageNavigation: Int) {
        /* val dataReceived: Response<MovieResponse> =
             movieService.topRatedMovieRequest(pageNavigation, API_KEY)
                 .execute()
         buildMoviesResponse(dataReceived, pageNavigation)*/
    }

    @Throws(IOException::class)
    fun fetchUpcomingMovies(pageNavigation: Int) {
        /* val dataReceived: Response<MovieResponse> =
             movieService.upcomingMovieRequest(pageNavigation, API_KEY)
                 .execute()
         buildMoviesResponse(dataReceived, pageNavigation)*/
    }

    @Throws(IOException::class)
    fun fetchNowPlayingMovies(pageNavigation: Int) {
        /*   val dataReceived: Response<MovieResponse> =
               movieService.playingMovieRequest(pageNavigation, API_KEY)
                   .execute()
           buildMoviesResponse(dataReceived, pageNavigation)*/
    }

    fun cleanFetchMovieList() {
        movies.value = ArrayList()
    }


}