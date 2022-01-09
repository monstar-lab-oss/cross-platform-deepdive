package com.github.ephelsa.okmoviesplace.android.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.ephelsa.okmoviesplace.android.ui.theme.Colors
import com.github.ephelsa.okmoviesplace.android.ui.theme.OKMoviesPlaceTheme
import com.github.ephelsa.okmoviesplace.android.ui.utils.clickableWithNoRipple

@Composable
fun PhilButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
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

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun PhilButtonPreview() {
    OKMoviesPlaceTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            PhilButton {
                Text(text = "All")
            }

            PhilButton(isSelected = true) {
                Text(text = "All")
            }

            PhilButton {
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