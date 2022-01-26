package com.cliabhach.terrapin.red.shell.search

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cliabhach.terrapin.red.shell.databinding.ViewSearchResultBinding

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
    }

}
