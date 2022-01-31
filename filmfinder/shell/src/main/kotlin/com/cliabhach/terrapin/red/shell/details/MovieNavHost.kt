package com.cliabhach.terrapin.red.shell.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.navigation.fragment.NavHostFragment
import com.cliabhach.terrapin.red.shell.R

/**
 * Placeholder class to avoid warning message in [R.layout.movie_details_activity].
 *
 * When [obtainFactory] is used, this will always be inflated as a [NavHostFragment].
 *
 * @author Philip Cohn-Cort
 */
class MovieNavHost : Fragment() {

    companion object {
        /**
         * A very simple factory to inflate [MovieNavHost] as if it were a [NavHostFragment].
         *
         * This is handy when you want to provide custom arguments to that fragment. For
         * example, starting with a specific item preselected.
         */
        fun obtainFactory(startNavArguments: Bundle?) = object : FragmentFactory() {
            override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                return if (className == MovieNavHost::class.java.name) {
                    NavHostFragment.create(
                        R.navigation.primary_details_nav_graph,
                        startNavArguments
                    )
                } else {
                    super.instantiate(classLoader, className)
                }
            }
        }
    }
}