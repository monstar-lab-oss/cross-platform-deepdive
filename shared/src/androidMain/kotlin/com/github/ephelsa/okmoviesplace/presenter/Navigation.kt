package com.github.ephelsa.okmoviesplace.presenter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import java.util.Deque
import java.util.LinkedList

actual class Navigation(
    private val context: Context,
) {
    private val history: Deque<Intent> = LinkedList()

    actual fun goTo(route: Router) {
        when (route) {
            Router.Favorites -> startActivity(
                action = "screen.favorites",
                flagList = listOf(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            )
            is Router.MovieDetails -> startActivity(
                action = "movie.details",
                bundleBuilder = {
                    putInt("movie_id", route.movieId)
                })
            Router.Movies -> startActivity(
                action = "screen.movies",
                flagList = listOf(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            )
        }
    }

    private fun startActivity(
        action: String,
        flagList: List<Int> = listOf(),
        bundleBuilder: (Bundle.() -> Unit) = { },
    ) {
        val intent = Intent(action).apply {
            flags += Intent.FLAG_ACTIVITY_NEW_TASK
            flagList.forEach { flags += it }
            putExtras(Bundle().apply(bundleBuilder))
        }
        history.push(intent)

        context.startActivity(intent)
    }

    actual fun back() {
        if (history.isNotEmpty()) {
            val intent = history.pop()

            if (context is Activity)
                context.finish()
            else
                context.startActivity(intent)
        }
    }
}