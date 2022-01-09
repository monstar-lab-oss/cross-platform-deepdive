package com.github.ephelsa.okmoviesplace.android.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import com.github.ephelsa.okmoviesplace.android.ui.screen.MoviesScreen
import com.github.ephelsa.okmoviesplace.android.ui.theme.OKMoviesPlaceTheme
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.instance

@ExperimentalMaterialApi
class MoviesActivity : ComponentActivity(), DIAware {

    override val di: DI by closestDI()

    private val viewModel: MoviesViewModel by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.movieGenres()

        setContent {
            OKMoviesPlaceTheme {
                MoviesScreen(viewModel)
            }
        }
    }
}