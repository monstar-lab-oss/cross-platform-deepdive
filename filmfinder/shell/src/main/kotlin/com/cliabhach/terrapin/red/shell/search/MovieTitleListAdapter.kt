package com.cliabhach.terrapin.red.shell.search

import android.view.ViewGroup
import android.widget.TextView

/**
 * Simple implementation of [SearchResultAdapter] that shows just the titles.
 */
class MovieTitleListAdapter : SearchResultAdapter<MovieTitleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTitleViewHolder {
        return MovieTitleViewHolder(TextView(parent.context))
    }

    override fun onBindViewHolder(holder: MovieTitleViewHolder, position: Int) {
        holder.view.text = getItem(position).title
    }

}
