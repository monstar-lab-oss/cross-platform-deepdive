package com.github.ephelsa.okmoviesplace.presenter

interface Navigation {
    fun goTo(route: Router)
    fun back()
}