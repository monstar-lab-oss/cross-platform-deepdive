package com.github.ephelsa.okmoviesplace.presenter

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class UserActionManager<SP : UIState, EP : UserAction>(
    dispatcher: CoroutineDispatcher,
    val navigation: Navigation,
) : UserActionCoroutineScope by UserActionCoroutineScope.UserActionCoroutineScopeImpl(dispatcher) {

    protected val state = MutableStateFlow<SP?>(null)
    val onState: StateFlow<SP?>
        get() = state.asStateFlow()

    init {
        createScope()
    }

    abstract fun runEvent(event: EP)
}

internal interface UserActionCoroutineScope : CoroutineScope {
    var job: Job
    val dispatcher: CoroutineDispatcher

    override val coroutineContext: CoroutineContext
        get() = dispatcher + job

    fun createScope() {
        job = SupervisorJob()
    }

    fun destroyScope() {
        job.cancel()
    }

    class UserActionCoroutineScopeImpl(
        override val dispatcher: CoroutineDispatcher,
    ) : UserActionCoroutineScope {
        override lateinit var job: Job
    }
}
