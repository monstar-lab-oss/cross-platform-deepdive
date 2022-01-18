package com.cliabhach.terrapin.red.shell

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.cliabhach.terrapin.red.shell.databinding.SearchFragmentBinding
import com.cliabhach.terrapin.red.shell.databinding.ViewSearchResultsBinding
import com.cliabhach.terrapin.red.shell.search.SearchViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * @author Philip Cohn-Cort
 */
class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel

    private lateinit var binding: SearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        val parent = view as ViewGroup

        val results = ViewSearchResultsBinding.inflate(layoutInflater, parent, false)
        results.root.adapter = EmptyAdapter()

        val searchBar = binding.searchInputBar
        val searchResults = binding.searchResultsContainer


        viewModel.queryFlow.filter {
            searchResults.visibility != View.GONE
        }.onEach {
            TODO("Search and modify the UI based on the result")
        }.launchIn(lifecycleScope)


        searchBar.setOnClickListener {
            searchResults.requestFocus()
        }
        searchBar.setNavigationOnClickListener {
            searchResults.requestFocus()
        }

        searchResults.addView(results.root)
        searchResults.setNavigationOnClickListener {
            searchResults.clearFocus()
        }
        searchResults.setOnQueryTextListener(viewModel.queryTextListener)
    }

}