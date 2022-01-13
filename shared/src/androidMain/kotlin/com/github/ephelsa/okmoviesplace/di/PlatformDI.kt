package com.github.ephelsa.okmoviesplace.di

import com.github.ephelsa.okmoviesplace.local.SQLDelightDriverFactory
import com.github.ephelsa.okmoviesplace.presenter.Navigation
import kotlinx.coroutines.Dispatchers
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

actual object PlatformDI {
    actual val diModule = DI.Module("Platform") {
        import(DataDI.repositoryModule)
        import(PresenterDI.viewManagerModule)
        import(androidModule)
        import(dispatchers)
    }

    private val androidModule = DI.Module("Platform/Android") {
        bindProvider { SQLDelightDriverFactory(instance()) }
        bindProvider { Navigation(instance()) }
    }

    private val dispatchers = DI.Module("Platform/Android/Dispatcher") {
        bindProvider(TagsDI.Dispatcher.IO) { Dispatchers.IO }
    }
}