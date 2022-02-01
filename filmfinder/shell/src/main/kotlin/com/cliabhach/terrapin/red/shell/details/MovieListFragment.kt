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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * The 'list' part of a list-details fragment pair.
 *
 * Refer to [MovieFragment] for the 'details' counterpart.
 */
class MovieListFragment : AbstractListDetailFragment() {

    private val listener: SearchListener by inject()

    private val detailsViewModel: MovieDetailsViewModel by sharedViewModel()

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

        // First, sort out the adapter
        val movieAdapter = MovieTitleListAdapter(
            onItemClick = { _, item ->
                detailPaneNavHostFragment.navController.navigate(
                    R.id.fragment_movie_detail,
                    Bundle(args).also { it.putInt(MovieFragment.ARG_MOVIE_ID, item.id) }
                )
            }
        )
        movieAdapter.setHasStableIds(true)

        val decor = IdempotentHighlightDecoration()

        binding.movieList.adapter = movieAdapter
        binding.movieList.removeItemDecoration(decor)
        binding.movieList.addItemDecoration(decor)

        detailsViewModel.movieIdFlow.onEach {
            decor.currentHighlightId = it.toLong()
            binding.movieList.invalidateItemDecorations()
        }.launchIn(lifecycleScope)

        // Second, make sure we're listening to queries correctly
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