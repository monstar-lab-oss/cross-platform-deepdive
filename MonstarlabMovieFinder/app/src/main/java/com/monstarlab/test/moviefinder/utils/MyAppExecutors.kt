package com.monstarlab.test.moviefinder.utils

import android.widget.TextView
import android.animation.AnimatorListenerAdapter
import android.os.Handler
import android.os.Looper
import com.monstarlab.test.moviefinder.utils.MyAppExecutors
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Helper class that handles and manage the different threads execution
 */
class MyAppExecutors
/**
 * Constructor
 *
 * @param dbThread      executor on charge of the access to the external storage
 * @param mainThread    executor on charge to notify to the ui thread
 * @param networkThread executor on charge to handle the network requests
 */ private constructor(
    val dBThread: Executor,
    val mainThread: Executor,
    val networkThread: Executor
) {

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }

    companion object {
        private val LOCK = Any()
        private const val NETWORK_THREAD_COUNT = 3
        private var sInstance: MyAppExecutors? = null
        @JvmStatic
        val instance: MyAppExecutors?
            get() {
                if (sInstance == null) {
                    synchronized(LOCK) {
                        sInstance = MyAppExecutors(
                            Executors.newSingleThreadExecutor(),
                            MainThreadExecutor(),
                            Executors.newFixedThreadPool(
                                NETWORK_THREAD_COUNT
                            )
                        )
                    }
                }
                return sInstance
            }
    }
}