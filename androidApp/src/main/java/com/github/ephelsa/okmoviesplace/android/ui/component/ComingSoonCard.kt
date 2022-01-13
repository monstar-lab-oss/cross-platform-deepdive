package com.github.ephelsa.okmoviesplace.android.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.github.ephelsa.okmoviesplace.android.ui.utils.shimmerEffectColor
import com.github.ephelsa.okmoviesplace.model.ImagePath

/**
 * Coming Soon card design.
 *
 * @param title is displayed at top of the card.
 * @param imagePath is the background image.
 * @param onClick click action when the icon is pressed.
 */
@Composable
fun ComingSoonCard(
    modifier: Modifier = Modifier,
    title: String?,
    imagePath: ImagePath?,
    onPlay: () -> Unit,
    onClick: () -> Unit,
) {
    val isDisplayable = title != null

    Surface(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .clickable(enabled = title != null, onClick = onClick),
        shape = Shapes.CardRoundedCornerShape,
        elevation = 4.dp,
    ) {
        Image(
            painter = rememberImagePainter(
                data = imagePath?.backdrop,
            ),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1.6f)
                .background(shimmerEffectColor())
        )

        if (isDisplayable) {
            Box(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.surface.copy(alpha = 0.1f))
                        .padding(
                            horizontal = 12.dp,
                            vertical = 14.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(0.8f),
                        text = title.toString(),
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
                            .size(20.dp),
                        tint = Colors.MintCream,
                    )
                }

                PlayButton(
                    modifier = Modifier.align(Alignment.Center),
                    playTitle = "",
                    onClick = onPlay
                )
            }
        }
    }
}

/**
 * ComingSoonCard in loading state
 */
@Composable
fun ComingSoonCard(
    modifier: Modifier = Modifier,
) {
    ComingSoonCard(
        modifier,
        null,
        null,
        {},
        {}
    )
}

@Preview
@Composable
private fun ComingSoonCardPreview() {
    OKMoviesPlaceTheme {
        ComingSoonCard(
            title = "Dora And The Lost City Of Gold",
            imagePath = ImagePath(
                "https://images.unsplash.com/photo-1433162653888-a571db5ccccf?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80",
                "https://images.unsplash.com/photo-1433162653888-a571db5ccccf?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80"
            ),
            onPlay = {},
            onClick = {}
        )
    }
}
