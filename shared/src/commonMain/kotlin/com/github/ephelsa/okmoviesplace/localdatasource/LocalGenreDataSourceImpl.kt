package com.github.ephelsa.okmoviesplace.localdatasource

import com.github.ephelsa.okmoviesplace.db.GenreQueries
import com.github.ephelsa.okmoviesplace.db.GenreTable
import com.github.ephelsa.okmoviesplace.model.Genre

class LocalGenreDataSourceImpl(
    private val genreQueries: GenreQueries
) : LocalGenreDataSource {

    override suspend fun storeMovieList(genres: List<Genre>) {
        genreQueries.transaction {
            genres.forEach { genre ->
                genreQueries.insertGenre(
                    GenreTable(genre.id.toLong(), genre.name)
                )
            }
        }
    }

    override suspend fun isMovieListEmpty(): Boolean {
        val result = genreQueries.isMovieListEmpty().executeAsOneOrNull()
        return result == 0L || result == null
    }

    override suspend fun movieById(id: Int): Genre? {
        return genreQueries.movieGenreById(id.toLong()) { identifier, name ->
            Genre(identifier.toInt(), name)
        }.executeAsOneOrNull()
    }
}