package com.github.ephelsa.okmoviesplace.android.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
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
import com.github.ephelsa.okmoviesplace.model.Genre
import com.github.ephelsa.okmoviesplace.model.Movie

@Composable
fun MovieCard(
    imageModifier: Modifier = Modifier,
    movie: Movie,
    showAll: Boolean,
    onClick: () -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Column(
        modifier = Modifier
            .width(screenWidth / 2)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberImagePainter(
                data = movie.imageUrl,
            ),
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = imageModifier
                .fillMaxWidth()
                .aspectRatio(0.8f)
                .clip(Shapes.CardRoundedCornerShape)
                .background(color = MaterialTheme.colors.secondary)
        )

        if (showAll) {
            PillButtonRowList(
                modifier = Modifier
                    .padding(top = Spaces.Medium),
            ) {
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
            }

            Text(
                text = movie.title,
                modifier = Modifier.padding(vertical = Spaces.MediumLow),
                style = TextStyle(
                    fontWeight = FontWeight.Light,
                    fontSize = 18.sp,
                    letterSpacing = 0.4.sp,
                    textAlign = TextAlign.Center,
                ),
            )
        }
    }
}

@Preview
@Composable
private fun MovieCardPreview() {
    val movie = Movie(
        id = 1,
        imageUrl = "https://images.unsplash.com/photo-1433162653888-a571db5ccccf?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80",
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