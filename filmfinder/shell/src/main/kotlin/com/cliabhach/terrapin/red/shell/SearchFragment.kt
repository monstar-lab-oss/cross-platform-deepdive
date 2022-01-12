package com.cliabhach.terrapin.red.shell

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.lapism.search.widget.MaterialSearchView

/**
 * @author Philip Cohn-Cort
 */
class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val parent = view as ViewGroup

        val results = layoutInflater.inflate(R.layout.view_search_results, parent, false)
        (results as RecyclerView).adapter = EmptyAdapter()

        val search = view.findViewById<MaterialSearchView>(R.id.search_results_container)
        search.addView(results)
    }

}