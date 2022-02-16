package com.monstarlab.test.moviefinder.view

import android.app.ActivityOptions
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.monstarlab.test.moviefinder.R
import com.monstarlab.test.moviefinder.databinding.ActivityMainBinding
import com.monstarlab.test.moviefinder.kmp.shared.ServiceLocator
import com.monstarlab.test.moviefinder.kmp.shared.data.KmmMovie
import com.monstarlab.test.moviefinder.kmp.shared.data.SortType
import com.monstarlab.test.moviefinder.kmp.shared.repository.PopularMoviesView
import com.monstarlab.test.moviefinder.provider.SearchSuggestionProvider
import com.monstarlab.test.moviefinder.utils.MyAppExecutors
import com.monstarlab.test.moviefinder.view.adapter.MovieRecycleViewAdapter
import com.monstarlab.test.moviefinder.viewmodel.MainViewModel

private const val LOG_TAG = "MainActivity"
private const val MOVIE_INTENT_EXTRA = "movie_to_detail"

class MainActivity : AppCompatActivity(), PopularMoviesView {
    private val presenter by lazy { ServiceLocator.moviesPresenter }

    private val mColumnCount = 2
    private var mViewModel: MainViewModel? = null
    private var adapter: MovieRecycleViewAdapter? = null
    private var mContext: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val movieRecyclerView = findViewById<RecyclerView>(R.id.movie_recycler_view)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.app_name)

        // Get the intent, verify the action and get the query
        handleIntent(intent)

        // Set the adapter
        if (mColumnCount <= 1) {
            movieRecyclerView.layoutManager = LinearLayoutManager(this)
        } else {
            movieRecyclerView.layoutManager = GridLayoutManager(this, mColumnCount)
        }

        adapter = MovieRecycleViewAdapter(this, mViewModel!!.movies.value!!)
        adapter!!.listener = object : MovieRecycleViewAdapter.OnNavigationListener {
            override fun onItemClick(view: View?, movie: KmmMovie?, position: Int) {
                val intent = Intent(mContext, DetailMovieActivity::class.java)
                intent.putExtra(MOVIE_INTENT_EXTRA, Gson().toJson(movie))
                startActivity(
                    intent,
                    ActivityOptions.makeSceneTransitionAnimation(this@MainActivity).toBundle()
                )
            }
        }

        movieRecyclerView.adapter = adapter
        mViewModel!!.movies.observeForever { movies: List<KmmMovie> ->
            Log.d(LOG_TAG, "KmmMovie list upload")
            MyAppExecutors.instance!!.mainThread.execute { adapter!!.setValues(movies) }
        }
        mViewModel!!.pageIndex.observeForever { page: Int ->
            Log.d(LOG_TAG, "next page change =$page")
            val sortType = mViewModel!!.sort.value
            pagingBySortType(sortType, page)
        }
        mViewModel!!.sort.observeForever { sortType: SortType ->
            Log.d(LOG_TAG, "sort change value=" + sortType.name)
            mViewModel!!.pageIndex.value = 1
            pagingBySortType(sortType, 1)
        }
    }

    override fun onResume() {
        super.onResume()
        pagingBySortType(mViewModel!!.sort.value, 1)
    }

    private fun pagingBySortType(sort: SortType?, page: Int) {
        when (sort) {
            SortType.FAVORITE -> if (page == 1) {
                MyAppExecutors.instance!!.dBThread.execute { mViewModel!!.startFavoriteMovieSearch() }
            }
            SortType.NEWEST -> MyAppExecutors.instance!!.networkThread.execute {
                mViewModel!!.startNewestMovieSearch(
                    page
                )
            }
            SortType.POPULAR -> MyAppExecutors.instance!!.networkThread.execute {
                mViewModel!!.startPopularMovieSearch(
                    page
                )
            }
            SortType.UPCOMING -> MyAppExecutors.instance!!.networkThread.execute {
                mViewModel!!.startUpcomingMovieSearch(
                    page
                )
            }
            SortType.TOP_RATED -> MyAppExecutors.instance!!.networkThread.execute {
                mViewModel!!.startTopRatedMovieSearch(
                    page
                )
            }
            SortType.NOW_PLAYING -> MyAppExecutors.instance!!.networkThread.execute {
                mViewModel!!.startNowPlayingMovieSearch(
                    page
                )
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntent(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the options menu from XML
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)

        // Get the SearchView and set the searchable configuration
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search_icon).actionView as SearchView
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.isIconifiedByDefault = false // Do not iconify the widget; expand it by default
        searchView.isSubmitButtonEnabled = true
        searchView.isQueryRefinementEnabled = true
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.filter_icon) {
            val menuItemView = findViewById<View>(R.id.filter_icon) // SAME ID AS MENU ID
            val popup = PopupMenu(mContext, menuItemView)
            popup.menuInflater.inflate(R.menu.menu_sort, popup.menu)
            popup.setOnMenuItemClickListener { it: MenuItem ->
                when (it.itemId) {
                    R.id.favorite_action -> mViewModel!!.sort.setValue(SortType.FAVORITE)
                    R.id.popular_action -> mViewModel!!.sort.setValue(SortType.POPULAR)
                    R.id.upcoming_action -> mViewModel!!.sort.setValue(SortType.UPCOMING)
                    R.id.top_rated_action -> mViewModel!!.sort.setValue(SortType.TOP_RATED)
                    R.id.now_playing_action -> mViewModel!!.sort.setValue(SortType.NOW_PLAYING)
                }
                true
            }
            popup.show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            // doMySearch(query);
            val suggestions = SearchRecentSuggestions(
                this,
                SearchSuggestionProvider.AUTHORITY,
                SearchSuggestionProvider.MODE
            )
            suggestions.saveRecentQuery(query, null)
            if (query!!.isEmpty()) {
                mViewModel!!.sort.setValue(SortType.POPULAR)
            } else {
                MyAppExecutors.instance!!.networkThread.execute {
                    mViewModel!!.startMovieSearch(
                        query,
                        1
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun setPopularMovies(movies: List<KmmMovie>) {
        Toast.makeText(this, "setPopularMovies = ${movies.size}", Toast.LENGTH_LONG).show()
    }

    override fun showMoviesFailedToLoad() {
        Toast.makeText(this, "Movies failed to load", Toast.LENGTH_LONG).show()
    }

    override fun setLoadingVisible(visible: Boolean) {
    }

}