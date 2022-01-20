package com.github.ephelsa.okmoviesplace.android.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.ephelsa.okmoviesplace.android.R
import com.github.ephelsa.okmoviesplace.android.ui.utils.clickableWithNoRipple
import com.github.ephelsa.okmoviesplace.presenter.Navigation
import com.github.ephelsa.okmoviesplace.presenter.Router

@Composable
fun DiscoveryFeature(
    navigation: Navigation,
    tabSelected: DiscoveryFeatureTab,
    content: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            Toolbar(tabSelected) {
                when (it) {
                    DiscoveryFeatureTab.Movies -> navigation.goTo(Router.MoviesRoute)
                    DiscoveryFeatureTab.Favorites -> navigation.goTo(Router.FavoritesRoute)
                }
            }
        },
    ) {
        // TODO: finish this part when navigation has been added
        content()
    }
}

/**
 * Toolbar's tab option
 */
enum class DiscoveryFeatureTab(
    @StringRes val titleRes: Int,
) {
    Movies(
        titleRes = R.string.label_movies,
    ),
    Favorites(
        titleRes = R.string.label_favorites,
    )
}

@Composable
private fun Toolbar(
    tabSelected: DiscoveryFeatureTab,
    onTabSelected: (DiscoveryFeatureTab) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DiscoveryFeatureTab.values().map { tab ->
                Text(
                    text = stringResource(tab.titleRes),
                    modifier = Modifier.clickableWithNoRipple { onTabSelected(tab) },
                    color = if (tabSelected == tab) {
                        MaterialTheme.colors.secondary
                    } else {
                        MaterialTheme.colors.secondaryVariant
                    }
                )
            }
        }
    }
}