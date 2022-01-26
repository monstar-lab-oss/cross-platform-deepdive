package com.github.ephelsa.okmoviesplace.android.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.github.ephelsa.okmoviesplace.android.ui.theme.Shapes
import com.github.ephelsa.okmoviesplace.android.ui.utils.shimmerEffectColor
import com.github.ephelsa.okmoviesplace.model.Actor

@ExperimentalCoilApi
@Composable
fun ActorCard(
    actor: Actor,
    onClick: () -> Unit,
) {
    val img = rememberImagePainter(data = actor.imagePath.poster)
    val modifier = Modifier
        .height(120.dp)
        .width(80.dp)
        .clip(Shapes.CardRoundedCornerShape)

    if (img.state is ImagePainter.State.Loading) {
        Box(modifier = modifier.background(shimmerEffectColor()))
    } else {
        Image(
            painter = img,
            contentDescription = actor.name,
            modifier = modifier.clickable(onClick = onClick),
            contentScale = ContentScale.Crop
        )
    }
}