package com.github.ephelsa.okmoviesplace.android

import android.app.Application
import com.github.ephelsa.okmoviesplace.di.PlatformDI
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule

class OkMoviesPlaceApplication : Application(), DIAware {

    override val di: DI by DI.lazy {
        import(androidXModule(this@OkMoviesPlaceApplication))
        importAll(PlatformDI.diModule)
    }
}