package com.github.ephelsa.okmoviesplace.android.ui.screen.moviedetails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.ephelsa.okmoviesplace.android.ui.theme.OKMoviesPlaceTheme
import com.github.ephelsa.okmoviesplace.presenter.Navigation
import com.github.ephelsa.okmoviesplace.presenter.moviedetails.MovieDetailsUserAction
import com.github.ephelsa.okmoviesplace.presenter.moviedetails.MovieDetailsUserActionManager
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.instance

class MovieDetailsActivity : ComponentActivity(), DIAware {

    override val di: DI by closestDI()

    private val navigation: Navigation by instance()
    private val actionManager: MovieDetailsUserActionManager by instance()

    private var movieId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            movieId = intent.extras!!.get("movie_id") as Int
        } catch (e: Exception) {
            navigation.back()
        }

        setContent {
            OKMoviesPlaceTheme {
                MovieDetailsScreen(navigation, actionManager)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        actionManager.action(MovieDetailsUserAction.LoadPage(movieId, 500))
    }

    override fun onDestroy() {
        super.onDestroy()
        actionManager.destroyScope()
    }
}