package com.cliabhach.terrapin.red.shell.details

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Specialised ViewModel for dealing with the movie-details UI.
 *
 * @author Philip Cohn-Cort
 */
class MovieDetailsViewModel: ViewModel() {

    private val _movieIdFlow = MutableStateFlow(-1)
    val movieIdFlow = _movieIdFlow.asStateFlow()

    /**
     * Only call this after the host activity has been created.
     *
     * These [arguments] can come from a Fragment, from the Activity's
     * Intent, or from the navigation graph. It doesn't really matter
     * as long as there's a [MovieFragment.ARG_MOVIE_ID] in there.
     */
    fun onActivityCreated(arguments: Bundle) {
        val initialId = arguments.getInt(MovieFragment.ARG_MOVIE_ID, -1)

        viewModelScope.launch {
            _movieIdFlow.emit(initialId)
        }
    }
}