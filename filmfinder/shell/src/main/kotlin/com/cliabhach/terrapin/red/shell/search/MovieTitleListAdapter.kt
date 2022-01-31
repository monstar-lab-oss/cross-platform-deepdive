package com.cliabhach.terrapin.red.shell.search

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cliabhach.terrapin.red.shell.databinding.ViewSearchResultBinding
import com.cliabhach.terrapin.red.shell.details.MovieHostActivity

/**
 * Simple implementation of [SearchResultAdapter] that shows just the titles.
 */
class MovieTitleListAdapter : SearchResultAdapter<MovieTitleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTitleViewHolder {
        return MovieTitleViewHolder(
            ViewSearchResultBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: MovieTitleViewHolder, position: Int) {
        holder.title.text = getItem(position).title

        // NB: Is it worth injecting a navigation tool here?
        holder.title.setOnClickListener {
            it.context.startActivity(
                MovieHostActivity.newActivityIntent(it, searchTerm, getItem(position))
            )
        }
    }

}
