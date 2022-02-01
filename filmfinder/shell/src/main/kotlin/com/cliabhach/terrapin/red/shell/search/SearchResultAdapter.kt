package com.cliabhach.terrapin.red.shell.search

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.cliabhach.terrapin.net.raw.movie.SearchResult

/**
 * Base class for Adapters to show lists of [movies][SearchResult].
 *
 * @author Philip Cohn-Cort
 * @see com.cliabhach.terrapin.net.Api
 */
abstract class SearchResultAdapter<VH : ViewHolder> : ListAdapter<SearchResult, VH>(
    object : DiffUtil.ItemCallback<SearchResult>() {
        override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
            return oldItem.title == newItem.title
        }
    }
) {

    /**
     * Current search query.
     *
     * Not intended for use directly by the rest of the
     * layout; it should only really be used to alter the
     * behaviour of [onBindViewHolder]. Set whenever the
     * list changes.
     */
    var searchTerm: CharSequence = ""

    override fun getItemId(position: Int): Long {
        return getItem(position).id.toLong()
    }
}