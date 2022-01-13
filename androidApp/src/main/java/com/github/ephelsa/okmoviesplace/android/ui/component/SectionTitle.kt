package com.github.ephelsa.okmoviesplace.android.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.github.ephelsa.okmoviesplace.android.ui.utils.shimmerEffectColor
import com.github.ephelsa.okmoviesplace.android.ui.utils.toDp

@Composable
fun SectionTitle(
    title: String?,
) {
    val fontStyle = MaterialTheme.typography.h1

    if (title != null) {
        Text(
            text = title,
            style = fontStyle,
        )
    } else {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp.value.toInt()
        val boxWidth = remember { ((screenWidth * 0.3).toInt()..screenWidth).random() }

        Box(
            Modifier
                .padding(top = 8.dp)
                .background(shimmerEffectColor())
                .width(boxWidth.dp)
                .height(fontStyle.fontSize.toDp())
        )
    }
}

/**
 * SectionTitle in loading state
 */
@Composable
fun SectionTitle() {
    SectionTitle(null)
}