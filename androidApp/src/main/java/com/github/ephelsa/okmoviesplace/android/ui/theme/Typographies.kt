package com.github.ephelsa.okmoviesplace.android.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object Typographies {
    val Default = Typography(
        h1 = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 34.sp,
            letterSpacing = (-1.5).sp
        ),
        h6 = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            letterSpacing = 0.15.sp
        )
    )

    val TagTextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp
    )
}
