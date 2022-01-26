package com.github.ephelsa.okmoviesplace.android.ui.screen.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.ephelsa.okmoviesplace.android.R
import com.github.ephelsa.okmoviesplace.android.ui.component.DiscoveryFeature
import com.github.ephelsa.okmoviesplace.android.ui.component.DiscoveryFeatureTab
import com.github.ephelsa.okmoviesplace.android.ui.component.MovieCard
import com.github.ephelsa.okmoviesplace.android.ui.component.SectionTitle
import com.github.ephelsa.okmoviesplace.android.ui.theme.Colors
import com.github.ephelsa.okmoviesplace.android.ui.theme.Spaces
import com.github.ephelsa.okmoviesplace.model.Movie
import com.github.ephelsa.okmoviesplace.presenter.Navigation
import com.github.ephelsa.okmoviesplace.presenter.Router
import com.github.ephelsa.okmoviesplace.presenter.favorites.FavoritesUIState
import com.github.ephelsa.okmoviesplace.presenter.favorites.FavoritesUserActionManager

@Composable
fun FavoritesScreen(
    navigation: Navigation,
    actionManager: FavoritesUserActionManager,
) {
    val favoritesState: FavoritesUIState? by actionManager.onState.collectAsState()

    DiscoveryFeature(navigation, DiscoveryFeatureTab.Favorites) {
        Column(
            modifier = Modifier
                .padding(horizontal = Spaces.Medium)
        ) {
            when (favoritesState) {
                FavoritesUIState.Empty -> EmptyFavoritesScreen()
                FavoritesUIState.Error -> TODO()
                FavoritesUIState.Loading, null -> {
                    CircularProgressIndicator()
                }
                is FavoritesUIState.Ready -> {
                    val ready = favoritesState as FavoritesUIState.Ready

                    MoviesSection(movies = ready.movies) { id ->
                        navigation.goTo(Router.MovieDetailsRoute(id))
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptyFavoritesScreen() {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(screenHeight)
            .alpha(0.3f),
        verticalArrangement = Arrangement.spacedBy(Spaces.MediumHigh, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.empty_noMoviesAddedYet),
            style = TextStyle(
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        )

        Icon(
            painter = painterResource(R.drawable.no_video),
            contentDescription = stringResource(R.string.contentDescription_noFavoriteMovies),
            tint = Colors.Onyx
        )
    }
}

@Composable
private fun MoviesSection(
    movies: List<Movie>,
    onMovie: (Int) -> Unit,
) {
    val columnMovies = movies.chunked(2)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Spaces.Medium),
        verticalArrangement = Arrangement.spacedBy(Spaces.Medium)
    ) {
        SectionTitle(stringResource(R.string.label_favoriteMovies))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Spaces.Medium)
        ) {
            items(columnMovies) { moviesRow ->

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(Spaces.Medium)
                ) {
                    items(moviesRow) {
                        MovieCard(movie = it, showAll = true) {
                            onMovie(it.id)
                        }
                    }
                }
            }
        }
    }
}