package com.github.ephelsa.okmoviesplace.android.ui.screen.favorites

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.github.ephelsa.okmoviesplace.android.ui.component.DiscoveryFeature
import com.github.ephelsa.okmoviesplace.android.ui.component.DiscoveryFeatureTab
import com.github.ephelsa.okmoviesplace.android.ui.component.MovieCard
import com.github.ephelsa.okmoviesplace.model.Movie
import com.github.ephelsa.okmoviesplace.presenter.favorites.FavoritesUIState
import com.github.ephelsa.okmoviesplace.presenter.favorites.FavoritesUserActionManager

@Composable
fun FavoritesScreen(
    actionManager: FavoritesUserActionManager,
) {
    val favoritesState: FavoritesUIState? by actionManager.onState.collectAsState()

    DiscoveryFeature(actionManager.navigation, DiscoveryFeatureTab.Favorites) {
        when (favoritesState) {
            FavoritesUIState.Error -> TODO()
            FavoritesUIState.Loading, null -> {
                CircularProgressIndicator()
            }
            is FavoritesUIState.Ready -> {
                val ready = favoritesState as FavoritesUIState.Ready

                LazyColumn {
                    items(ready.favorites) {
                        // TODO: Temporal
                        MovieCard(
                            movie = Movie(
                                id = it.id,
                                genres = it.genres,
                                imagePath = it.imagePath,
                                isAdult = it.isAdult,
                                title = it.title,
                                votesAverage = it.votesAverage
                            ),
                            showAll = true
                        ) {
                            // TODO
                        }
                    }
                }
            }
        }
    }
}
