package com.github.ephelsa.okmoviesplace.android.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.ephelsa.okmoviesplace.android.R
import com.github.ephelsa.okmoviesplace.android.ui.utils.clickableWithNoRipple

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onFavorite: () -> Unit,
) {
    Icon(
        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.Favorite,
        contentDescription = stringResource(R.string.contentDescription_addToFavorite),
        modifier = Modifier
            .size(30.dp)
            .clickableWithNoRipple(onClick = onFavorite),
        tint = if (isFavorite) MaterialTheme.colors.secondary else MaterialTheme.colors.secondaryVariant
    )
}