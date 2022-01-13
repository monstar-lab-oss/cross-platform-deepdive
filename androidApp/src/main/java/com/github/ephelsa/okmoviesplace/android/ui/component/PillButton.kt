package com.github.ephelsa.okmoviesplace.android.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.ephelsa.okmoviesplace.android.R
import com.github.ephelsa.okmoviesplace.android.ui.theme.Colors
import com.github.ephelsa.okmoviesplace.android.ui.theme.OKMoviesPlaceTheme
import com.github.ephelsa.okmoviesplace.android.ui.theme.Spaces
import com.github.ephelsa.okmoviesplace.android.ui.theme.Typographies
import com.github.ephelsa.okmoviesplace.android.ui.utils.clickableWithNoRipple
import com.github.ephelsa.okmoviesplace.android.ui.utils.shimmerEffectColor

@Composable
fun PillButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    val color = if (isSelected) {
        MaterialTheme.colors.secondary
    } else {
        MaterialTheme.colors.primary
    }

    Surface(
        modifier = modifier
            .background(
                color = color,
                shape = RoundedCornerShape(4.dp)
            )
            .clickableWithNoRipple { onClick?.invoke() }
            .padding(8.dp),
        color = color,
        contentColor = MaterialTheme.colors.secondaryVariant,
        content = content
    )
}

/**
 * PillButton in loading state
 */
@Composable
fun PillButton(
    modifier: Modifier = Modifier,
) {
    val width = remember { (40..200).random() }

    Box(
        modifier
            .background(shimmerEffectColor())
            .width(width.dp)
            .height(20.dp)
            .padding(8.dp),
    )
}

@Composable
fun PillTextButton(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    PillButton(
        modifier = modifier,
        isSelected = isSelected,
        onClick = onClick
    ) {
        Text(
            text = text,
            style = Typographies.TagTextStyle
        )
    }
}

@Composable
fun PillVotesButton(
    modifier: Modifier = Modifier,
    votes: Double,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    PillButton(
        modifier = modifier,
        isSelected = isSelected,
        onClick = onClick
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(Spaces.ThinHigh, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = stringResource(R.string.contentDescription_votesAverage, votes),
                tint = Colors.Mustard,
                modifier = Modifier.height(14.dp)
            )
            Text(
                text = votes.toString(),
                style = Typographies.TagTextStyle
            )
        }
    }
}

@Composable
fun PillButtonRowList(
    modifier: Modifier = Modifier,
    content: LazyListScope.() -> Unit,
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Spaces.MediumLow),
        content = content
    )
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun PillButtonPreview() {
    OKMoviesPlaceTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            PillButton {
                Text(text = "All")
            }

            PillButton(isSelected = true) {
                Text(text = "All")
            }

            PillButton {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = Colors.Mustard
                    )
                    Text(text = "All")
                }
            }
        }
    }
}