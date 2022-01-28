package com.cliabhach.terrapin.red.shell.details


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
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

        // The field 'navController' isn't usable...wonder if we need to retrieve it manually?

        if (savedInstanceState == null) {
            val initialId = intent.getIntExtra(MovieFragment.ARG_MOVIE_ID, -1)
            // TODO: Should navigate so as to show that movie. Or maybe pull it in in the fragment?
        }

        barConfig = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, barConfig)
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