package com.cliabhach.terrapin.red.shell.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.AbstractListDetailFragment
import androidx.navigation.fragment.NavHostFragment
import com.cliabhach.terrapin.net.filtered.movie.SearchResultsPage
import com.cliabhach.terrapin.red.shell.R
import com.cliabhach.terrapin.red.shell.databinding.MovieListFragmentBinding
import com.cliabhach.terrapin.red.shell.search.MovieTitleListAdapter
import com.cliabhach.terrapin.red.shell.search.SearchListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

/**
 * The 'list' part of a list-details fragment pair.
 *
 * Refer to [MovieFragment] for the 'details' counterpart.
 */
class MovieListFragment : AbstractListDetailFragment() {

    private val listener: SearchListener by inject()

    override fun onCreateListPaneView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MovieListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onListPaneViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = MovieListFragmentBinding.bind(view)

        val args = requireArguments()
        val query = args.getCharSequence(ARG_SEARCH_TERM, "")

        val movieAdapter = MovieTitleListAdapter()

        binding.movieList.adapter = movieAdapter

        movieAdapter.searchTerm = query
        lifecycleScope.launch {
            // Hopefully this will use the cache
            when(val search = listener.search(query)) {
                SearchResultsPage.Empty -> {
                    // Do nothing?
                    movieAdapter.submitList(listOf())
                }
                is SearchResultsPage.Results -> {
                    movieAdapter.submitList(search.list)
                }
                is SearchResultsPage.Unusable -> {
                    Snackbar.make(view, search.message, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onCreateDetailPaneNavHostFragment(): NavHostFragment {
        return NavHostFragment.create(R.navigation.primary_details_sub_nav_graph, arguments)
    }

    companion object {
        const val ARG_SEARCH_TERM = "terrapin:search_term"
    }
}