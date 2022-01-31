package com.cliabhach.terrapin.red.shell.details

import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import com.cliabhach.terrapin.red.shell.R

/**
 * Need to call this in 2 circumstances:
 * - When screen first loads from link (e.g. from HomeActivity)
 * - When item in MovieListFragment is clicked
 *
 * This corresponds to 3 places:
 * - MovieHostActivity.onCreate or MovieListFragment.onCreate or similar (initial call in portrait)
 * - MovieHostActivity.onCreate or MovieListFragment.onCreate or similar (initial call in landscape)
 * - MovieListFragment or MovieDetailsViewModel (click event on list)
 *
 * In portrait, we don't care about calling this when the details screen is already up...right?
 *
 * @author Philip Cohn-Cort
 */
fun showDetails(topHost: NavHostFragment, embeddedHost: NavHostFragment?, movieId: Int) {
    val bundle = bundleOf(MovieFragment.ARG_MOVIE_ID to movieId)
    if (embeddedHost != null) {
        // There is a NavHostFragment elsewhere in the hierarchy which
        // is responsible for showing movie details. Use it.
        // Expected graph: [R.navigation.primary_details_sub_nav_graph]
        embeddedHost.navController.navigate(R.id.fragment_movie_detail, bundle)
    } else {
        // The NavHostFragment which contains 'topHost' is always capable
        // of switching to show movie details, so ask it to do so.
        // Expected graph: [R.navigation.primary_details_nav_graph]
        topHost.navController.navigate(R.id.show_movie_detail, bundle)
    }
}