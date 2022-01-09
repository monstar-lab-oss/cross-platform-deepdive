package com.github.ephelsa.okmoviesplace.android.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val ColorPalette = lightColors(
    primary = Colors.RaisingBlack,
    primaryVariant = Colors.RichBlackFOGA29,
    secondary = Colors.ImperialRed,
    secondaryVariant = Colors.MintCream,
    background = Colors.RichBlackFOGA29,
    surface = Colors.RichBlackFOGA29,
)

@Composable
fun OKMoviesPlaceTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = ColorPalette,
        content = content,
        typography = Typographies.Default
    )
}