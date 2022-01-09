package com.github.ephelsa.okmoviesplace.android.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val ColorPalette = lightColors(
    primary = RaisingBlack,
    primaryVariant = RichBlackFOGA29,
    secondary = ImperialRed,
    secondaryVariant = ImperialRed,
    background = RichBlackFOGA29,
    surface = RichBlackFOGA29,
)

@Composable
fun OKMoviesPlaceTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = ColorPalette,
        content = content,
        typography = Typography
    )
}