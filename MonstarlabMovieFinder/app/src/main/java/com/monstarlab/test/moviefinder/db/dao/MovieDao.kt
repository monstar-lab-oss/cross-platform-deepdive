package com.monstarlab.test.moviefinder.db.dao

import androidx.room.*
import com.monstarlab.test.moviefinder.db.entity.Movie
import com.monstarlab.test.moviefinder.kmp.shared.data.KmmMovie

@Dao
interface MovieDao {
    @get:Query("SELECT * FROM movie")
    val all: List<Movie>

    @Query("SELECT * FROM movie WHERE id == :id")
    fun getFavoriteMovieById(id: Int): Movie?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)
}