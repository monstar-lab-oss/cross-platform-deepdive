package com.monstarlab.test.moviefinder.kmp.shared.domain

import kotlin.coroutines.CoroutineContext

expect val defaultDispatcher: CoroutineContext

expect val uiDispatcher: CoroutineContext