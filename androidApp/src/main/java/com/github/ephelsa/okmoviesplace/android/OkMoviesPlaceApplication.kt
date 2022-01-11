package com.github.ephelsa.okmoviesplace.android

import android.app.Application
import com.github.ephelsa.okmoviesplace.android.di.AppDI
import com.github.ephelsa.okmoviesplace.di.CommonDI
import com.github.ephelsa.okmoviesplace.local.SQLDelightDriverFactory
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.bindProvider

class OkMoviesPlaceApplication : Application(), DIAware {

    override val di: DI by DI.lazy {
        import(androidXModule(this@OkMoviesPlaceApplication))

        bindProvider { SQLDelightDriverFactory(this@OkMoviesPlaceApplication) }

        import(CommonDI.repositoryModule)
        import(AppDI.viewModelModule)
    }
}