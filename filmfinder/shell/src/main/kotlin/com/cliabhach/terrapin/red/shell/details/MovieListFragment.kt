package com.cliabhach.terrapin.red.shell.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.AbstractListDetailFragment
import androidx.navigation.fragment.NavHostFragment
import com.cliabhach.terrapin.red.shell.EmptyAdapter
import com.cliabhach.terrapin.red.shell.R
import com.cliabhach.terrapin.red.shell.databinding.MovieListFragmentBinding

/**
 * The 'list' part of a list-details fragment pair.
 *
 * Refer to [MovieFragment] for the 'details' counterpart.
 */
class MovieListFragment : AbstractListDetailFragment() {

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
        // TODO: Use the search term to create a search-like adapter
        args.keySet()

        binding.movieList.adapter = EmptyAdapter()
    }

    override fun onCreateDetailPaneNavHostFragment(): NavHostFragment {
        return NavHostFragment.create(R.navigation.primary_details_sub_nav_graph, arguments)
    }

    /**
     * Wrapper for a call to [showDetails].
     */
    @Deprecated("Handle this with onCreateDetailPaneNavHostFragment instead")
    private fun navigateToDetails(
        activity: FragmentActivity,
        binding: MovieListFragmentBinding,
        topNavHost: NavHostFragment
    ) {
        val initialId = activity.intent.getIntExtra(MovieFragment.ARG_MOVIE_ID, -1)

        val embeddedNavFragment = null/*binding.movieDetailNavContainer?.run {
            childFragmentManager.findFragmentById(id)
        }*/

        showDetails(
            topNavHost,
            embeddedNavFragment as? NavHostFragment,
            initialId
        )
    }

    companion object {
        const val ARG_SEARCH_TERM = "terrapin:search_term"
    }
}