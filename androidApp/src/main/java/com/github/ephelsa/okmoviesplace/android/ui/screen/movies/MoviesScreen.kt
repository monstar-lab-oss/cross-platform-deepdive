package com.github.ephelsa.okmoviesplace.android.ui.screen.movies

import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.ephelsa.okmoviesplace.android.R
import com.github.ephelsa.okmoviesplace.android.ui.component.ComingSoonCard
import com.github.ephelsa.okmoviesplace.android.ui.component.DiscoveryFeature
import com.github.ephelsa.okmoviesplace.android.ui.component.DiscoveryFeatureTab
import com.github.ephelsa.okmoviesplace.android.ui.component.MovieCard
import com.github.ephelsa.okmoviesplace.android.ui.component.PillButton
import com.github.ephelsa.okmoviesplace.android.ui.component.PillButtonRowList
import com.github.ephelsa.okmoviesplace.android.ui.component.PillTextButton
import com.github.ephelsa.okmoviesplace.android.ui.component.SectionTitle
import com.github.ephelsa.okmoviesplace.android.ui.theme.Spaces
import com.github.ephelsa.okmoviesplace.model.Genre
import com.github.ephelsa.okmoviesplace.model.Movie
import com.github.ephelsa.okmoviesplace.presenter.Navigation
import com.github.ephelsa.okmoviesplace.presenter.Router
import com.github.ephelsa.okmoviesplace.presenter.movies.MoviesUIState
import com.github.ephelsa.okmoviesplace.presenter.movies.MoviesUserActionManager

@Composable
fun MoviesScreen(
    navigation: Navigation,
    actionManager: MoviesUserActionManager,
) {
    val moviesState: MoviesUIState? by actionManager.onState.collectAsState()

    DiscoveryFeature(navigation, DiscoveryFeatureTab.Movies) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(Spaces.MediumHigh)
        ) {
            when (moviesState) {
                MoviesUIState.Error -> TODO()
                MoviesUIState.FirstIn -> TODO()
                MoviesUIState.Loading, null -> {
                    LoadingMoviesScreen()
                }
                is MoviesUIState.Ready -> {
                    val content = (moviesState as MoviesUIState.Ready)

                    ComingSoonSection(content.comingSoonMovies)
                    GenresSection(content.movieGenres)
                    TrendingNowSection(content.trendingNowMovies) {
                        navigation.goTo(Router.MovieDetailsRoute(it.id))
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingMoviesScreen() {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val trendingListState = rememberScrollState()

    LaunchedEffect(Unit) {
        trendingListState.scrollBy(screenWidth.value)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Spaces.Medium),
        verticalArrangement = Arrangement.spacedBy(Spaces.Medium)
    ) {
        SectionTitle()
        ComingSoonCard()

        PillButtonRowList {
            item { PillButton() }
            item { PillButton() }
            item { PillButton() }
            item { PillButton() }
        }

        SectionTitle()
        Row(
            modifier = Modifier.horizontalScroll(trendingListState, false),
        ) {
            val trendingCardOffset = screenWidth * 0.1f

            MovieCard(
                modifier = Modifier
                    .scale(0.7f)
                    .offset(trendingCardOffset),
                showAll = false
            )
            MovieCard(
                modifier = Modifier.scale(0.9f),
                showAll = true
            )
            MovieCard(
                modifier = Modifier
                    .scale(0.7f)
                    .offset(-trendingCardOffset),
                showAll = false
            )
        }
    }
}

@Composable
fun ComingSoonSection(
    upcomingMovies: List<Movie>,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardSize = screenWidth - (Spaces.Medium * 2)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Spaces.Medium),
        verticalArrangement = Arrangement.spacedBy(Spaces.Medium)
    ) {
        SectionTitle(stringResource(R.string.label_comingSoon))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(Spaces.Medium)
        ) {
            items(upcomingMovies) {
                ComingSoonCard(
                    modifier = Modifier.width(cardSize),
                    title = it.title,
                    imagePath = it.imagePath,
                    onClick = {
                        // TODO
                    },
                    onPlay = {
                        // TODO
                    }
                )
            }
        }
    }
}

@Composable
fun GenresSection(
    movieGenres: List<Genre>,
) {
    PillButtonRowList(
        modifier = Modifier
            .padding(horizontal = Spaces.Medium)
    ) {
        items(movieGenres) {
            PillTextButton(text = it.name)
        }
    }
}

@Composable
fun TrendingNowSection(
    trendingMovies: List<Movie>,
    onMovieSelected: (Movie) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Spaces.Medium)
    ) {
        Text(
            text = stringResource(R.string.label_trendingNow),
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .padding(horizontal = Spaces.Medium)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(Spaces.Medium)
        ) {
            items(trendingMovies) {
                MovieCard(
                    movie = it,
                    showAll = true,
                    onClick = { onMovieSelected(it) }
                )
            }
        }
    }
}