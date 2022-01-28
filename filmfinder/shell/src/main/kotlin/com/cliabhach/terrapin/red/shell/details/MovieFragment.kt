package com.cliabhach.terrapin.red.shell.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cliabhach.terrapin.red.shell.databinding.MovieDetailsFragmentBinding

/**
 * The 'details' part of a list-details fragment pair.
 *
 * Refer to [MovieListFragment] for the 'list' counterpart.
 */
class MovieFragment : Fragment() {

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

        binding.movieDetail.text = "No text yet!"
    }

    companion object {

        const val ARG_MOVIE_ID = "terrapin.movie_id"
    }
}