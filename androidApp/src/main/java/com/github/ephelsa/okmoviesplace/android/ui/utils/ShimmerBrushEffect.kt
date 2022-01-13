package com.github.ephelsa.okmoviesplace.android.ui.utils

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Brush
import com.github.ephelsa.okmoviesplace.android.ui.theme.Colors

@Composable
fun shimmerEffectColor(): Brush {
    val colorOffset = 0.4f

    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.35f,
        animationSpec = infiniteRepeatable(
            animation = tween(1_000),
            repeatMode = RepeatMode.Reverse
        ))
    val distance by infiniteTransition.animateFloat(
        initialValue = colorOffset * -1f,
        targetValue = 1f + colorOffset,
        animationSpec = infiniteRepeatable(
            animation = tween(1_000),
            repeatMode = RepeatMode.Reverse
        )
    )

    return Brush.horizontalGradient(
        distance to Colors.Onyx.copy(alpha = alpha),
        distance + (colorOffset / 2f) to Colors.Onyx.copy(alpha = alpha + 0.01f),
        distance + colorOffset to Colors.Onyx.copy(alpha = alpha),
    )
}