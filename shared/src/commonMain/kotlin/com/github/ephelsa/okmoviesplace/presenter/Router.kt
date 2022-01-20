package com.github.ephelsa.okmoviesplace.presenter

sealed class Router {
    object MoviesRoute : Router()
    object FavoritesRoute : Router()
    data class MovieDetailsRoute(val movieId: Int) : Router()
}