package com.github.ephelsa.okmoviesplace.android.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.github.ephelsa.okmoviesplace.android.R
import com.github.ephelsa.okmoviesplace.android.ui.theme.Colors
import com.github.ephelsa.okmoviesplace.android.ui.theme.OKMoviesPlaceTheme
import com.github.ephelsa.okmoviesplace.android.ui.theme.Shapes

/**
 * Coming Soon card design.
 *
 * @param title is displayed at top of the card.
 * @param imagePath is the background image.
 * @param onClick click action when the icon is pressed.
 */
@ExperimentalMaterialApi
@Composable
fun ComingSoonCard(
    title: String,
    imagePath: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.6f),
        shape = Shapes.CardRoundedCornerShape,
        elevation = 4.dp,
    ) {
        Image(
            painter = rememberImagePainter(data = imagePath),
            contentDescription = title,
            contentScale = ContentScale.FillBounds
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = 14.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(0.8f),
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h6,
                color = Colors.MintCream
            )

            Icon(
                imageVector = Icons.Outlined.Send,
                contentDescription = stringResource(R.string.contentDescription_movieDetails),
                modifier = Modifier
                    .rotate(-45.0f)
                    .size(20.dp)
                    .clickable(onClick = onClick),
                tint = Colors.MintCream,
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
private fun ComingSoonCardPreview() {
    OKMoviesPlaceTheme {
        ComingSoonCard("Dora And The Lost City Of Gold", "https://images.unsplash.com/photo-1433162653888-a571db5ccccf?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80") {

        }
    }
}
