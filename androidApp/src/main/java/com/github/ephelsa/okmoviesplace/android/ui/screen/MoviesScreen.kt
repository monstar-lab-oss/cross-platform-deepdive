package com.github.ephelsa.okmoviesplace.android.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.ephelsa.okmoviesplace.android.R
import com.github.ephelsa.okmoviesplace.android.movies.MoviesViewModel
import com.github.ephelsa.okmoviesplace.android.ui.component.ComingSoonCard
import com.github.ephelsa.okmoviesplace.android.ui.component.DiscoveryFeature
import com.github.ephelsa.okmoviesplace.android.ui.component.DiscoveryFeatureTab
import com.github.ephelsa.okmoviesplace.android.ui.component.PhilButton
import com.github.ephelsa.okmoviesplace.android.ui.theme.Spaces

@ExperimentalMaterialApi
@Composable
fun MoviesScreen(
    viewModel: MoviesViewModel,
) {
    DiscoveryFeature(DiscoveryFeatureTab.Movies) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(Spaces.MediumHigh)
        ) {
            ComingSoonSection(
                title = "Dora And The Lost City Of Gold",
                imageUrl = "https://images.unsplash.com/photo-1433162653888-a571db5ccccf?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80"
            )

            CategoriesSection(viewModel)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun ComingSoonSection(
    title: String,
    imageUrl: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Spaces.MediumSpace),
        verticalArrangement = Arrangement.spacedBy(Spaces.MediumSpace)
    ) {
        Text(
            text = stringResource(R.string.label_comingSoon),
            style = MaterialTheme.typography.h1
        )

        ComingSoonCard(title = title, imageUrl = imageUrl) {

        }
    }
}

@Composable
fun CategoriesSection(
    viewModel: MoviesViewModel,
) {
    val movieGenres by viewModel.onMovieGenres.collectAsState()

    LazyRow(
        modifier = Modifier
            .padding(horizontal = Spaces.MediumSpace),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movieGenres ?: emptyList()) {
            PhilButton {
                Text(
                    text = it.name,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}