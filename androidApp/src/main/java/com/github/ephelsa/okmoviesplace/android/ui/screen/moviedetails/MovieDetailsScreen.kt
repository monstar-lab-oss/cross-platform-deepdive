package com.github.ephelsa.okmoviesplace.android.ui.screen.moviedetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.github.ephelsa.okmoviesplace.android.ui.component.ComingSoonCard
import com.github.ephelsa.okmoviesplace.android.ui.component.NavigationBackToolbar
import com.github.ephelsa.okmoviesplace.android.ui.utils.shimmerEffectColor
import com.github.ephelsa.okmoviesplace.model.MovieDetails
import com.github.ephelsa.okmoviesplace.presenter.Navigation
import com.github.ephelsa.okmoviesplace.presenter.moviedetails.MovieDetailsUIState
import com.github.ephelsa.okmoviesplace.presenter.moviedetails.MovieDetailsUserActionManager
import kotlin.math.min

@Composable
fun MovieDetailsScreen(
    navigation: Navigation,
    actionManager: MovieDetailsUserActionManager,
) {
    val state by actionManager.onState.collectAsState()
    val scrollState = rememberScrollState()

    Surface(modifier = Modifier.fillMaxWidth()) {
        NavigationBackToolbar(navigation = navigation)
        // TODO: Move Column below ParallaxImage and add here a ConstraintLayout
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            when (state) {
                MovieDetailsUIState.Error -> TODO()
                MovieDetailsUIState.Loading, null -> LoadingMovieDetailsScreen()
                MovieDetailsUIState.OperationDone -> TODO()
                is MovieDetailsUIState.Ready -> {
                    val ready = state as MovieDetailsUIState.Ready
                    ParallaxImage(ready.movieDetails, scrollState)
                    ComingSoonCard()
                    ComingSoonCard()
                    ComingSoonCard()
                    ComingSoonCard()
                }
            }
        }
    }
}

@Composable
private fun LoadingMovieDetailsScreen() {
    CircularProgressIndicator()
}

@ExperimentalCoilApi
@Composable
private fun ParallaxImage(
    movieDetails: MovieDetails,
    scrollState: ScrollState,
) {
    val localConfiguration = LocalConfiguration.current
    val screenWidth = localConfiguration.screenWidthDp.dp
    val screenHeight = localConfiguration.screenHeightDp.dp
    val imagePainter = rememberImagePainter(
        data = movieDetails.imagePath.poster,
    )
    val modifier = Modifier
        .width(screenWidth)
        .aspectRatio(0.8f)
        .graphicsLayer {
            alpha = min(1f, 1 - (scrollState.value / screenHeight.value))
            translationY = -scrollState.value * 0.1f
        }

    if (imagePainter.state is ImagePainter.State.Loading) {
        Box(modifier = modifier.background(shimmerEffectColor()))
    }

    Image(
        painter = imagePainter,
        contentDescription = movieDetails.title,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}
