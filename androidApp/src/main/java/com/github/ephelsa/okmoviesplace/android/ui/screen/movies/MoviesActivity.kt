package com.github.ephelsa.okmoviesplace.android.ui.screen.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import com.github.ephelsa.okmoviesplace.android.ui.theme.OKMoviesPlaceTheme
import com.github.ephelsa.okmoviesplace.di.TagsDI
import com.github.ephelsa.okmoviesplace.presenter.movies.MoviesUserAction
import com.github.ephelsa.okmoviesplace.presenter.movies.MoviesUserActionManager
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.instance

@ExperimentalMaterialApi
class MoviesActivity : ComponentActivity(), DIAware {

    override val di: DI by closestDI()
    private val actionManager: MoviesUserActionManager by instance(TagsDI.Presenter.Movies)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        actionManager.runEvent(MoviesUserAction.LoadPage(500, 500))

        setContent {
            OKMoviesPlaceTheme {
                MoviesScreen(actionManager)
            }
        }
    }

    override fun onDestroy() {
        actionManager.destroyScope()
        super.onDestroy()
    }
}