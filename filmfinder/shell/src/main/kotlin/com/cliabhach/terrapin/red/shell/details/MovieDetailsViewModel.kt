package com.cliabhach.terrapin.red.shell.details

import android.app.Activity
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

    fun onActivityCreated(activity: Activity) {
        val initialId = activity.intent.getIntExtra(MovieFragment.ARG_MOVIE_ID, -1)

        viewModelScope.launch {
            _movieIdFlow.emit(initialId)
        }
    }
}