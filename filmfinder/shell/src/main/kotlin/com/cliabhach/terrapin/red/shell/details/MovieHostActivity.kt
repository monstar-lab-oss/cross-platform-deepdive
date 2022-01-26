package com.cliabhach.terrapin.red.shell.details


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
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

        barConfig = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, barConfig)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(barConfig) || super.onSupportNavigateUp()
    }
}