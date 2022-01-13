package com.github.ephelsa.okmoviesplace.android.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.github.ephelsa.okmoviesplace.android.R
import com.github.ephelsa.okmoviesplace.android.ui.theme.OKMoviesPlaceTheme
import com.github.ephelsa.okmoviesplace.android.ui.theme.Shapes
import com.github.ephelsa.okmoviesplace.android.ui.theme.Spaces
import com.github.ephelsa.okmoviesplace.android.ui.utils.shimmerEffectColor
import com.github.ephelsa.okmoviesplace.android.ui.utils.toDp
import com.github.ephelsa.okmoviesplace.model.Genre
import com.github.ephelsa.okmoviesplace.model.ImagePath
import com.github.ephelsa.okmoviesplace.model.Movie

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movie: Movie?,
    showAll: Boolean,
    onClick: () -> Unit,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val titleStyle = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 18.sp,
        letterSpacing = 0.4.sp,
        textAlign = TextAlign.Center,
    )

    Column(
        modifier = modifier
            .width(screenWidth / 2)
            .clickable(enabled = movie != null, onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberImagePainter(
                data = movie?.imagePath?.poster,
            ),
            contentDescription = movie?.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.8f)
                .clip(Shapes.CardRoundedCornerShape)
                .background(shimmerEffectColor())
        )

        if (showAll) {
            PillButtonRowList(
                modifier = Modifier
                    .padding(top = Spaces.Medium),
            ) {
                if (movie != null) {
                    item {
                        if (movie.isAdult) {
                            PillTextButton(text = stringResource(R.string.label_adultContent))
                        }
                    }

                    item {
                        if (movie.genres.isNotEmpty()) {
                            PillTextButton(text = movie.genres[0].name)
                        }
                    }

                    item {
                        PillVotesButton(votes = movie.votesAverage)
                    }
                } else {
                    item {
                        PillButton()
                    }
                }
            }

            val textPadding = Modifier.padding(vertical = Spaces.MediumLow)

            if (movie != null) {
                Text(
                    text = movie.title,
                    modifier = textPadding,
                    style = titleStyle,
                )
            } else {
                Box(textPadding
                    .fillMaxWidth()
                    .height(titleStyle.fontSize.toDp())
                    .background(shimmerEffectColor()))
            }
        }
    }
}

/**
 * MovieCard in loading state
 */
@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    showAll: Boolean,
) {
    MovieCard(
        modifier = modifier,
        movie = null,
        showAll = showAll,
        onClick = {}
    )
}

@Preview
@Composable
private fun MovieCardPreview() {
    val movie = Movie(
        id = 1,
        imagePath = ImagePath(
            "https://images.unsplash.com/photo-1433162653888-a571db5ccccf?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80",
            "https://images.unsplash.com/photo-1433162653888-a571db5ccccf?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80"
        ),
        title = "Angel Has Fallen asasdasdasdasdasdasdasdasdasdasd",
        isAdult = true,
        votesAverage = 5.0,
        genres = listOf(
            Genre(1, "Action")
        )
    )

    OKMoviesPlaceTheme {
        MovieCard(
            movie = movie,
            showAll = true
        ) {

        }
    }
}