package com.github.ephelsa.okmoviesplace.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.ephelsa.okmoviesplace.android.theme.Mustard
import com.github.ephelsa.okmoviesplace.android.theme.OKMoviesPlaceTheme

class OkMoviesPlaceActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OKMoviesPlaceTheme {
                OkMoviesPlaceApp()
            }
        }
    }
}

@Composable
fun OkMoviesPlaceApp() {
}
