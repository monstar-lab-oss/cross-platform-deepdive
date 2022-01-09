package com.github.ephelsa.okmoviesplace.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.github.ephelsa.okmoviesplace.android.ui.component.ComingSoonCard
import com.github.ephelsa.okmoviesplace.android.ui.theme.OKMoviesPlaceTheme

@ExperimentalMaterialApi
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

@ExperimentalMaterialApi
@Composable
fun OkMoviesPlaceApp() {
    ComingSoonCard(
        title = "Dora And The Lost City Of Gold",
        imageUrl = "https://images.unsplash.com/photo-1433162653888-a571db5ccccf?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80"
    ) {

    }
}

