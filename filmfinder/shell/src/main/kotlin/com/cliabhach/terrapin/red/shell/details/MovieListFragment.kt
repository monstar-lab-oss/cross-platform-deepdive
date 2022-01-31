package com.cliabhach.terrapin.red.shell.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import com.cliabhach.terrapin.red.shell.EmptyAdapter
import com.cliabhach.terrapin.red.shell.databinding.MovieListFragmentBinding

/**
 * The 'list' part of a list-details fragment pair.
 *
 * Refer to [MovieFragment] for the 'details' counterpart.
 */
class MovieListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MovieListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = MovieListFragmentBinding.bind(view)

        binding.movieList.adapter = EmptyAdapter()

        val activity = requireActivity()

        val topNavHost = activity.supportFragmentManager.primaryNavigationFragment

        if (topNavHost is NavHostFragment) {
            navigateToDetails(activity, binding, topNavHost)
        }
    }

    /**
     * Wrapper for a call to [showDetails].
     */
    private fun navigateToDetails(
        activity: FragmentActivity,
        binding: MovieListFragmentBinding,
        topNavHost: NavHostFragment
    ) {
        val initialId = activity.intent.getIntExtra(MovieFragment.ARG_MOVIE_ID, -1)

        val embeddedNavFragment = binding.movieDetailNavContainer?.run {
            childFragmentManager.findFragmentById(id)
        }

        showDetails(
            topNavHost,
            embeddedNavFragment as? NavHostFragment,
            initialId
        )
    }
}