package com.cliabhach.terrapin.red.shell.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lapism.search.widget.MaterialSearchView
import com.lapism.search.widget.MaterialSearchView.OnQueryTextListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Specialised ViewModel for dealing with the movie-search UI.
 */
class SearchViewModel : ViewModel() {

    private val _queryFlow = MutableStateFlow<CharSequence>("")
    val queryFlow = _queryFlow.asStateFlow()

    /**
     * A simple listener for your [MaterialSearchView].
     *
     * This emits new text queries into [queryFlow] whenever the submit button
     * (`enter` or `send`, usually) is pressed.
     */
    val queryTextListener: OnQueryTextListener = object : OnQueryTextListener {
        override fun onQueryTextChange(newText: CharSequence) {
            // Do nothing
        }

        override fun onQueryTextSubmit(query: CharSequence) {
            viewModelScope.launch {
                _queryFlow.emit(query)
            }
        }
    }

}