package com.github.ephelsa.okmoviesplace.presenter

sealed class Router {
    object Movies : Router()
    object Favorites : Router()
    data class MovieDetails(val movieId: Int) : Router()
}