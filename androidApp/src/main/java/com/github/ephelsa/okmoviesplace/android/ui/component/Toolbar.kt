package com.github.ephelsa.okmoviesplace.android.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.github.ephelsa.okmoviesplace.android.R
import com.github.ephelsa.okmoviesplace.android.ui.theme.Spaces
import com.github.ephelsa.okmoviesplace.presenter.Navigation
import com.github.ephelsa.okmoviesplace.presenter.Router

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    leftContent: @Composable () -> Unit,
) {
    Row(
        modifier = modifier
            .padding(Spaces.Small)
            .height(40.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        leftContent()
    }
}

@Composable
fun NavigationBackToolbar(
    modifier: Modifier = Modifier,
    navigation: Navigation,
) {
    Toolbar(
        modifier = modifier
            .padding(top = Spaces.MediumHigh)
            .zIndex(100f)
    ) {
        Icon(
            imageVector = Icons.Rounded.KeyboardArrowLeft,
            contentDescription = stringResource(id = R.string.contentDescription_navigateBack),
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxHeight()
                .clip(CircleShape)
                .clickable { navigation.back() },
            tint = MaterialTheme.colors.secondaryVariant
        )
    }
}

@Preview
@Composable
private fun ToolbarPreview() {
    Toolbar {
        Text(text = "Hello")
    }
}

@Preview
@Composable
private fun NavigationBackToolbarPreview() {
    NavigationBackToolbar(navigation = object : Navigation {
        override fun goTo(route: Router) {
            // Not implemented
        }

        override fun back() {
            // Not implemented
        }
    })
}