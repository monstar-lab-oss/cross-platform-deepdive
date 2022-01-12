package com.cliabhach.terrapin.red.shell

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cliabhach.terrapin.red.shell.databinding.SearchFragmentBinding
import com.cliabhach.terrapin.red.shell.databinding.ViewSearchResultsBinding
import com.lapism.search.widget.MaterialSearchView

/**
 * @author Philip Cohn-Cort
 */
class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var binding: SearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val parent = view as ViewGroup

        val results = ViewSearchResultsBinding.inflate(layoutInflater, parent, false)
        results.root.adapter = EmptyAdapter()

        val searchBar = binding.searchInputBar
        val searchResults = binding.searchResultsContainer


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
        searchResults.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: CharSequence) {
                // Do nothing
            }

            override fun onQueryTextSubmit(query: CharSequence) {
                searchResults.clearFocus()
            }
        })
    }

}