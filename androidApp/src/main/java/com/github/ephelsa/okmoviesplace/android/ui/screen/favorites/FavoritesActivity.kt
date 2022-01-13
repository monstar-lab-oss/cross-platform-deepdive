package com.github.ephelsa.okmoviesplace.android.ui.screen.favorites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.ephelsa.okmoviesplace.android.ui.theme.OKMoviesPlaceTheme
import com.github.ephelsa.okmoviesplace.di.TagsDI
import com.github.ephelsa.okmoviesplace.presenter.favorites.FavoritesUserAction
import com.github.ephelsa.okmoviesplace.presenter.favorites.FavoritesUserActionManager
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.instance

class FavoritesActivity : ComponentActivity(), DIAware {

    override val di: DI by closestDI()

    private val actionManager: FavoritesUserActionManager by instance(TagsDI.Presenter.Favorites)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        actionManager.action(FavoritesUserAction.LoadPage(500))

        setContent {
            OKMoviesPlaceTheme {
                FavoritesScreen(actionManager)
            }
        }
    }

    override fun onDestroy() {
        actionManager.destroyScope()
        super.onDestroy()
    }
}