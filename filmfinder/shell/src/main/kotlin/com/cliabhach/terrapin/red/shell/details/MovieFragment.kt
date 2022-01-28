package com.cliabhach.terrapin.red.shell.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.cliabhach.terrapin.net.Api
import com.cliabhach.terrapin.red.shell.databinding.MovieDetailsFragmentBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

/**
 * The 'details' part of a list-details fragment pair.
 *
 * Refer to [MovieListFragment] for the 'list' counterpart.
 */
class MovieFragment : Fragment() {

    internal val api: Api by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MovieDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = MovieDetailsFragmentBinding.bind(view)

        val movieId = requireArguments().getInt(ARG_MOVIE_ID, -1)

        binding.movieDetail.text = "No text yet!"

        if (movieId != -1) {
            // TODO: Request and bind details
            lifecycleScope.launch {
                // Make the api call here
            }
        }

    }

    companion object {

        const val ARG_MOVIE_ID = "terrapin:movie_id"
    }
}