package com.github.ephelsa.okmoviesplace.android

import android.app.Application
import com.github.ephelsa.okmoviesplace.di.CommonDI
import com.github.ephelsa.okmoviesplace.localdatasource.SQLDelightDriverFactory
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.bind
import org.kodein.di.instance

class OkMoviesPlaceApplication : Application(), DIAware {

    override val di: DI by DI.lazy {
        import(androidXModule(this@OkMoviesPlaceApplication))

        bind { instance(SQLDelightDriverFactory(this@OkMoviesPlaceApplication)) }

        import(CommonDI.repositoryModule)
    }
}