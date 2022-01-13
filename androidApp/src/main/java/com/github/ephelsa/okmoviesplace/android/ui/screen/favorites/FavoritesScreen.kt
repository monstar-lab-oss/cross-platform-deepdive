package com.github.ephelsa.okmoviesplace.android.ui.screen.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.github.ephelsa.okmoviesplace.android.ui.component.PillButtonRowList
import com.github.ephelsa.okmoviesplace.android.ui.component.PillTextButton
import com.github.ephelsa.okmoviesplace.android.ui.component.SectionTitle
import com.github.ephelsa.okmoviesplace.android.ui.theme.Colors
import com.github.ephelsa.okmoviesplace.android.ui.theme.Spaces
import com.github.ephelsa.okmoviesplace.model.Genre
import com.github.ephelsa.okmoviesplace.presenter.favorites.FavoritesUIState
import com.github.ephelsa.okmoviesplace.presenter.favorites.FavoritesUserAction
import com.github.ephelsa.okmoviesplace.presenter.favorites.FavoritesUserActionManager

@Composable
fun FavoritesScreen(
    actionManager: FavoritesUserActionManager,
) {
    val favoritesState: FavoritesUIState? by actionManager.onState.collectAsState()

    DiscoveryFeature(actionManager.navigation, DiscoveryFeatureTab.Favorites) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            when (favoritesState) {
                FavoritesUIState.Empty -> EmptyFavoritesScreen()
                FavoritesUIState.Error -> TODO()
                FavoritesUIState.Loading, null -> {
                    CircularProgressIndicator()
                }
                is FavoritesUIState.Ready -> {
                    val ready = favoritesState as FavoritesUIState.Ready
                    GenresSection(ready.genres) { actionManager.action(FavoritesUserAction.FilterByGenre(it)) }
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
private fun GenresSection(
    genres: List<Genre>,
    onGenreSelected: (Genre) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Spaces.Medium)
    ) {
        SectionTitle(stringResource(R.string.label_yourFavoriteCategories))

        val columns = genres.chunked(5)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(Spaces.MediumLow)
        ) {
            items(columns) { chunked ->
                PillButtonRowList {
                    items(chunked) {
                        PillTextButton(text = it.name) { onGenreSelected(it) }
                    }
                }
            }
        }
    }
}