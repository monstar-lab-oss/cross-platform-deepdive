package com.github.ephelsa.okmoviesplace.android.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.ephelsa.okmoviesplace.android.R
import com.github.ephelsa.okmoviesplace.android.ui.theme.OKMoviesPlaceTheme

@Composable
fun PlayButton(
    modifier: Modifier = Modifier,
    playTitle: String,
    onClick: () -> Unit,
) {
    Surface(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick),
        color = MaterialTheme.colors.secondary
    ) {
        Icon(
            imageVector = Icons.Rounded.PlayArrow,
            contentDescription = stringResource(R.string.contentDescription_playButton, playTitle),
            tint = MaterialTheme.colors.secondaryVariant
        )
    }
}

@Preview
@Composable
private fun PlayButtonPreview() {
    OKMoviesPlaceTheme {
        PlayButton(playTitle = "Dora And The Last City Of Gold") {
            // Do nothing
        }
    }
}