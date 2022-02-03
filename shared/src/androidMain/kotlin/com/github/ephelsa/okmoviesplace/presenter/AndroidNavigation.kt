package com.github.ephelsa.okmoviesplace.presenter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import java.util.Deque
import java.util.LinkedList

internal class AndroidNavigation(
    private val context: Context,
) : Navigation {

    private val history: Deque<Intent> = LinkedList()

    init {
        val initialActivity = createIntent(
            action = IntentActions.MOVIES,
            flagList = listOf(Intent.FLAG_ACTIVITY_NO_ANIMATION),
            bundleBuilder = { /* Empty content. No Bundle actions */ }
        )
        history.push(initialActivity)
    }

    override fun goTo(route: Router) {
        when (route) {
            Router.FavoritesRoute -> startActivity(
                action = IntentActions.FAVORITES,
                flagList = listOf(
                    Intent.FLAG_ACTIVITY_NO_ANIMATION,
                )
            )
            is Router.MovieDetailsRoute -> startActivity(
                action = IntentActions.MOVIE_DETAILS,
                flagList = listOf(
                    Intent.FLAG_ACTIVITY_NO_ANIMATION,
                ),
                bundleBuilder = {
                    putInt("movie_id", route.movieId)
                })
            Router.MoviesRoute -> startActivity(
                action = IntentActions.MOVIES,
                flagList = listOf(
                    Intent.FLAG_ACTIVITY_NO_ANIMATION,
                )
            )
        }
    }

    private fun startActivity(
        action: String,
        flagList: List<Int> = listOf(),
        bundleBuilder: (Bundle.() -> Unit) = { },
    ) {
        val intent = createIntent(action, flagList, bundleBuilder)

        history.push(intent)
        context.startActivity(intent)
    }

    private fun createIntent(
        action: String,
        flagList: List<Int>,
        bundleBuilder: (Bundle.() -> Unit),
    ): Intent {
        val intent = Intent(action).apply {
            flags += Intent.FLAG_ACTIVITY_NEW_TASK
            flagList.forEach { flags += it }
            putExtras(Bundle().apply(bundleBuilder))
        }

        return intent
    }

    override fun back() {
        try {
            history.pop() // Pop the current activity
            context.startActivity(history.last())
        } catch (e: Exception) {
            Log.v(AndroidNavigation::class.simpleName, "Navigation history is empty")
        }
    }
}