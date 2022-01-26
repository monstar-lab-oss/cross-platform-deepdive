package com.github.ephelsa.okmoviesplace.android.ui.screen.moviedetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.github.ephelsa.okmoviesplace.android.R
import com.github.ephelsa.okmoviesplace.android.ui.component.ActorCard
import com.github.ephelsa.okmoviesplace.android.ui.component.FavoriteButton
import com.github.ephelsa.okmoviesplace.android.ui.component.NavigationBackToolbar
import com.github.ephelsa.okmoviesplace.android.ui.component.PillButton
import com.github.ephelsa.okmoviesplace.android.ui.component.PillButtonRowList
import com.github.ephelsa.okmoviesplace.android.ui.component.PillTextButton
import com.github.ephelsa.okmoviesplace.android.ui.component.PillVotesButton
import com.github.ephelsa.okmoviesplace.android.ui.component.PlayButton
import com.github.ephelsa.okmoviesplace.android.ui.component.SectionTitle
import com.github.ephelsa.okmoviesplace.android.ui.theme.Colors
import com.github.ephelsa.okmoviesplace.android.ui.theme.Spaces
import com.github.ephelsa.okmoviesplace.android.ui.utils.shimmerEffectColor
import com.github.ephelsa.okmoviesplace.model.Actor
import com.github.ephelsa.okmoviesplace.model.MovieDetails
import com.github.ephelsa.okmoviesplace.presenter.Navigation
import com.github.ephelsa.okmoviesplace.presenter.moviedetails.MovieDetailsUIState
import com.github.ephelsa.okmoviesplace.presenter.moviedetails.MovieDetailsUserActionManager
import kotlin.math.max
import kotlin.math.min

@ExperimentalCoilApi
@Composable
fun MovieDetailsScreen(
    navigation: Navigation,
    actionManager: MovieDetailsUserActionManager,
) {
    val state by actionManager.onState.collectAsState()
    val scrollState = rememberScrollState()

    // TODO: Move Column below ParallaxImage and add here a ConstraintLayout
    ConstraintLayout(
        modifier = Modifier.padding(bottom = Spaces.MediumHigh)
    ) {
        val (toolbar, content) = createRefs()

        // Constraint references
        NavigationBackToolbar(
            modifier = Modifier.constrainAs(toolbar) {
                top.linkTo(parent.top)
            },
            navigation = navigation,
        )

        Column(
            modifier = Modifier
                .constrainAs(content) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .verticalScroll(scrollState)
        ) {
            when (state) {
                MovieDetailsUIState.Error -> TODO()
                MovieDetailsUIState.Loading, null -> LoadingMovieDetailsScreen()
                is MovieDetailsUIState.Ready -> {
                    val ready = state as MovieDetailsUIState.Ready
                    var isFavorite by remember { mutableStateOf(ready.isMovieFavorite) }

                    ParallaxImage(
                        movieDetails = ready.movieDetails,
                        scrollState = scrollState
                    )

                    Column(modifier = Modifier.padding(horizontal = Spaces.Medium)) {
                        TagsAndFavorite(
                            movieDetails = ready.movieDetails,
                            isFavorite = isFavorite,
                        ) {
                            if (isFavorite) {
                                actionManager.removeFavorite(ready.movieDetails.id) {
                                    isFavorite = false
                                }
                            } else {
                                actionManager.saveFavorite(ready.movieDetails.id) {
                                    isFavorite = true
                                }
                            }
                        }
                        Description(movieDetails = ready.movieDetails)
                        Casting(casting = ready.casting) {
                            // TODO: Actor click
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadingMovieDetailsScreen() {
    Box(
        modifier = Modifier
            .width(LocalConfiguration.current.screenWidthDp.dp)
            .aspectRatio(0.7f)
            .background(shimmerEffectColor())
    )

    Column(
        modifier = Modifier.padding(horizontal = Spaces.Medium),
        verticalArrangement = Arrangement.spacedBy(Spaces.MediumHigh)
    ) {
        PillButtonRowList {
            items(2) {
                PillButton()
            }
        }

        PillButtonRowList {
            items(5) {
                PillButton()
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(Spaces.MediumHigh)
        ) {
            SectionTitle()
            Box(
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .background(shimmerEffectColor())
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(Spaces.MediumHigh)
        ) {
            SectionTitle()
        }
    }
}

@ExperimentalCoilApi
@Composable
private fun ParallaxImage(
    modifier: Modifier = Modifier,
    movieDetails: MovieDetails,
    scrollState: ScrollState,
) {
    val localConfiguration = LocalConfiguration.current
    val screenWidth = localConfiguration.screenWidthDp.dp
    val screenHeight = localConfiguration.screenHeightDp.dp
    val imagePainter = rememberImagePainter(
        data = movieDetails.imagePath.poster,
    )
    val colorSurface = MaterialTheme.colors.surface

    Box {
        Box(
            modifier = modifier
                .width(screenWidth)
                .aspectRatio(0.7f)
                .drawWithContent {
                    drawContent()
                    drawRect(
                        brush = Brush.verticalGradient(
                            0.7f to Color.Transparent,
                            1f to colorSurface
                        ),
                    )
                }
                .graphicsLayer {
                    val newAlpha = max(0.2f, 1 - (scrollState.value / screenHeight.value * 0.5f))
                    alpha = min(1f, newAlpha)
                }
        ) {
            if (imagePainter.state is ImagePainter.State.Loading) {
                Box(modifier = Modifier.background(shimmerEffectColor()))
            }

            Image(
                modifier = Modifier.fillMaxSize(),
                painter = imagePainter,
                contentDescription = movieDetails.title,
                contentScale = ContentScale.Crop
            )

            PlayButton(
                modifier = Modifier.align(Alignment.Center),
                playTitle = movieDetails.title
            ) {
                // TODO
            }
        }

        Text(
            text = movieDetails.duration.toString(),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(Spaces.Medium),
            color = MaterialTheme.colors.secondaryVariant,
            style = MaterialTheme.typography.caption
        )
    }
}

@Composable
private fun TagsAndFavorite(
    movieDetails: MovieDetails,
    isFavorite: Boolean,
    onFavorite: (Int) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Spaces.Medium)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PillButtonRowList {
                if (movieDetails.isAdult) {
                    item {
                        PillTextButton(text = stringResource(R.string.label_adultContent))
                    }
                }

                item {
                    PillVotesButton(votes = movieDetails.votesAverage)
                }
            }

            FavoriteButton(isFavorite = isFavorite) {
                onFavorite(movieDetails.id)
            }
        }

        PillButtonRowList {
            items(movieDetails.genres) { genres ->
                PillTextButton(text = genres.name)
            }
        }
    }
}

@Composable
private fun Description(
    movieDetails: MovieDetails,
) {
    Column(
        modifier = Modifier.padding(top = Spaces.Medium),
        verticalArrangement = Arrangement.spacedBy(Spaces.Medium)
    ) {
        SectionTitle(title = movieDetails.title)
        Text(
            text = movieDetails.description,
            color = Colors.MintCream,
            style = MaterialTheme.typography.body2
        )
    }
}

@ExperimentalCoilApi
@Composable
private fun Casting(
    casting: List<Actor>,
    onActorClick: (Actor) -> Unit,
) {
    Column(
        modifier = Modifier.padding(top = Spaces.Medium),
        verticalArrangement = Arrangement.spacedBy(Spaces.Medium)
    ) {
        SectionTitle(title = stringResource(id = R.string.label_casting))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(Spaces.MediumLow)
        ) {
            items(casting) {
                ActorCard(it) { onActorClick(it) }
            }
        }
    }
}