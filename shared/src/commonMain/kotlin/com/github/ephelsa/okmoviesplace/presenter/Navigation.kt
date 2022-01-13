package com.github.ephelsa.okmoviesplace.presenter

// One per platform
expect class Navigation {
    fun goTo(route: Router)
    fun back()
}