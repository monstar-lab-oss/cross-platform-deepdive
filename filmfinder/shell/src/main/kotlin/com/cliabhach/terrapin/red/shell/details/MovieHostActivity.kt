package com.cliabhach.terrapin.red.shell.details


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.cliabhach.terrapin.net.raw.movie.SearchResult
import com.cliabhach.terrapin.red.shell.R
import com.cliabhach.terrapin.red.shell.databinding.MovieDetailsActivityBinding

/**
 * Host for [MovieListFragment]s and [MovieFragment]s.
 *
 * For a general-purpose search screen, use
 * [com.cliabhach.terrapin.red.shell.HomeActivity] instead.
 */
class MovieHostActivity : AppCompatActivity() {

    /**
     * Toolbar/AppBar config.
     *
     * This can update the window title and back button and
     * stuff when navigating from one fragment to another.
     */
    private lateinit var barConfig: AppBarConfiguration

    // Maybe there's a nice way to use the Lifecycle for this?
    // It's probably fine as is.
    private val navController: NavController
        get() {
            return findNavController(R.id.movie_details_nav_host_fragment)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = MovieDetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val topNavHost = getNavHostBeforeOnStart()
        // Similarly, the field 'navController' isn't usable until
        // NavHostFragment::onViewCreated gets called.
        val controller = (topNavHost as NavHostFragment).navController

        if (savedInstanceState == null) {
            // Default Navigation is handled by MovieListFragment (our default destination)
        }

        barConfig = AppBarConfiguration(controller.graph)
        setupActionBarWithNavController(controller, barConfig)
    }

    /**
     * Special alternative to [FragmentManager.getPrimaryNavigationFragment].
     *
     * If we wait long enough, the FragmentManager will eventually choose our
     * main [NavHostFragment] as its primary navigation fragment (there aren't
     * any other candidates, so it's not much of a contest). This method will
     * find and set that fragment as the primary nav, then return it.
     *
     * Designed for use from [onCreate] and [onStart]. At any later time, it's
     * better to just query [getSupportFragmentManager] directly.
     */
    @MainThread
    private fun getNavHostBeforeOnStart(): Fragment? {
        val fm: FragmentManager = supportFragmentManager
        val topNavHost = fm.findFragmentById(R.id.movie_details_nav_host_fragment)
        fm.beginTransaction()
            .setReorderingAllowed(true)
            .setPrimaryNavigationFragment(topNavHost)
            .commitNow()
        return topNavHost
    }

    internal val ARG_MOVIE_RESULT = "terrapin:movie_search_result"

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(barConfig) || super.onSupportNavigateUp()
    }

    companion object {

        fun newActivityIntent(source: View, initial: SearchResult): Intent {
            val context = source.context

            return Intent(context, MovieHostActivity::class.java).apply {
                putExtra(MovieFragment.ARG_MOVIE_ID, initial.id)
            }
        }
    }
}