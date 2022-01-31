package com.cliabhach.terrapin.red.shell

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.cliabhach.terrapin.net.filtered.movie.SearchResultsPage
import com.cliabhach.terrapin.red.shell.databinding.SearchFragmentBinding
import com.cliabhach.terrapin.red.shell.databinding.ViewSearchResultsBinding
import com.cliabhach.terrapin.red.shell.details.MovieHostActivity
import com.cliabhach.terrapin.red.shell.search.MovieTitleListAdapter
import com.cliabhach.terrapin.red.shell.search.SearchListener
import com.cliabhach.terrapin.red.shell.search.SearchResultAdapter
import com.cliabhach.terrapin.red.shell.search.SearchViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject

/**
 * @author Philip Cohn-Cort
 */
class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel

    private lateinit var binding: SearchFragmentBinding

    private val listener: SearchListener by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchFragmentBinding.inflate(inflater, container, false)

        // Make the search box's EditText (defined in material_search_view.xml) match our theme.
        binding.searchResultsContainer
            .findViewById<EditText>(R.id.search_view_edit_text)
            .apply {
                setTextAppearance(R.style.Keratin_TextAppearance_SearchText)
            }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        val parent = view as ViewGroup

        val results = ViewSearchResultsBinding.inflate(layoutInflater, parent, false)

        val mainAdapter = obtainMainAdapter(results)

        val searchBar = binding.searchInputBar
        val searchResults = binding.searchResultsContainer


        viewModel.queryFlow.filter {
            searchResults.visibility != View.GONE
        }.onEach {
            when(val search = listener.search(it)) {
                SearchResultsPage.Empty -> {
                    searchResults.setTextColor(Color.BLUE)
                }
                is SearchResultsPage.Results -> {
                    searchResults.setTextColor(Color.RED)
                    mainAdapter.searchTerm = it
                    mainAdapter.submitList(search.list)
                }
                is SearchResultsPage.Unusable -> {
                    searchResults.setTextColor(Color.BLACK)
                    Snackbar.make(searchResults, search.message, Snackbar.LENGTH_LONG).show()
                }
            }
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

    /**
     * Retrieve a RecyclerView adapter for showing search results in a list.
     *
     * We try to be economical - if there is already a compatible adapter on
     * the given binding, then we'll just return that. We create and attach
     * [MovieTitleListAdapter]s when no other adapter is currently available.
     */
    private fun obtainMainAdapter(results: ViewSearchResultsBinding): SearchResultAdapter<*> {
        val currentAdapter = results.root.adapter

        val mainAdapter = if (currentAdapter is SearchResultAdapter) {
            currentAdapter
        } else {
            MovieTitleListAdapter(
                onItemClick = { view, item ->
                    val source = view.context

                    source.startActivity(
                        MovieHostActivity.newActivityIntent(source, searchTerm, item)
                    )
                }
            ).also {
                results.root.adapter = it
            }
        }
        return mainAdapter
    }

}