package com.cliabhach.terrapin.red.shell.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cliabhach.terrapin.net.raw.movie.SearchResult
import com.cliabhach.terrapin.red.shell.databinding.ViewSearchResultBinding

/**
 * Simple implementation of [SearchResultAdapter] that shows just the titles.
 *
 * The [onItemClick] should e.g. navigate to a new destination in the host's
 * [NavGraph][androidx.navigation.NavGraph]. It'll be called whenever one of
 * the items is clicked.
 */
class MovieTitleListAdapter(
    val onItemClick: MovieTitleListAdapter.(View, SearchResult) -> Unit
) : SearchResultAdapter<MovieTitleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTitleViewHolder {
        return MovieTitleViewHolder(
            ViewSearchResultBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: MovieTitleViewHolder, position: Int) {
        holder.title.text = getItem(position).title

        // Details screen needs different behavior from home screen
        holder.title.setOnClickListener {
            onItemClick(it, getItem(position))
        }
    }

}
