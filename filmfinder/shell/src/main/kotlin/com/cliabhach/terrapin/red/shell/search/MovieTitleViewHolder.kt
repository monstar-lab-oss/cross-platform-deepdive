package com.cliabhach.terrapin.red.shell.search

import androidx.recyclerview.widget.RecyclerView
import com.cliabhach.terrapin.red.shell.databinding.ViewSearchResultBinding

/**
 * @author Philip Cohn-Cort
 */
class MovieTitleViewHolder(
    binding: ViewSearchResultBinding
) : RecyclerView.ViewHolder(binding.root) {

    val title = binding.text1

}