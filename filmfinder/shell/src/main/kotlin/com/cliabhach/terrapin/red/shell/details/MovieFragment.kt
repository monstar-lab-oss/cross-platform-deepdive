package com.cliabhach.terrapin.red.shell.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.cliabhach.terrapin.net.Api
import com.cliabhach.terrapin.net.filtered.movie.MovieDetails
import com.cliabhach.terrapin.red.shell.databinding.MovieDetailsFragmentBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject

/**
 * The 'details' part of a list-details fragment pair.
 *
 * Refer to [MovieListFragment] for the 'list' counterpart.
 */
class MovieFragment : Fragment() {

    private lateinit var viewModel: MovieDetailsViewModel

    internal val api: Api by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MovieDetailsViewModel::class.java]
        activity?.let { viewModel.onActivityCreated(it) }
    }

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

        binding.movieDetailTitle.text = "No text yet!"

        if (movieId != -1) {
            viewModel.movieIdFlow
                .onEach { id ->
                    when (val details = api.getMovieDetails(id)) {
                        is MovieDetails.Result -> {
                            binding.movieDetailTitle.text = details.title
                            binding.movieDetailTagline.text = details.tagline
                        }
                        is MovieDetails.Unusable -> {
                            binding.movieDetailTagline.text = details.message
                            Snackbar.make(binding.movieDetailTitle, details.message, Snackbar.LENGTH_LONG).show()
                        }
                    }
                }.launchIn(lifecycleScope)
        }

    }

    companion object {

        const val ARG_MOVIE_ID = "terrapin:movie_id"
    }
}