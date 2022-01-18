package com.cliabhach.terrapin.red.eared

import android.app.Application
import com.cliabhach.terrapin.di.netModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * @author Philip Cohn-Cort
 */
class RedApplication: Application() {

    override fun onCreate() {
        super.onCreate()


        // Initialise dependency injection
        startKoin {
            androidLogger(level = Level.ERROR)
            androidContext(applicationContext)
            modules(netModule)
        }
    }
}