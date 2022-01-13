package com.github.ephelsa.okmoviesplace.coroutines

import co.touchlab.stately.freeze
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue

/**
 * Dispatcher to execute work in another thread for iOS.
 */
@ThreadLocal
internal object MainDispatcher : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatch_get_main_queue()) {
            try {
                // We need to freeze to make the object shareable between threads/workers
                block.run().freeze()
            } catch (err: Throwable) {
                throw err
            }
        }
    }
}