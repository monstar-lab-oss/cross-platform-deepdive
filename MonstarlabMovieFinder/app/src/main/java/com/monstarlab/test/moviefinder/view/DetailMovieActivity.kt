package com.monstarlab.test.moviefinder.view

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.monstarlab.test.moviefinder.R
import com.monstarlab.test.moviefinder.databinding.ActivityDetailBinding
import com.monstarlab.test.moviefinder.db.entity.Movie
import com.monstarlab.test.moviefinder.kmp.shared.data.KmmMovie
import com.monstarlab.test.moviefinder.utils.AnimationHelper
import com.monstarlab.test.moviefinder.utils.MyAppExecutors.Companion.instance
import com.monstarlab.test.moviefinder.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso

private const val MOVIE_INTENT_EXTRA = "movie_to_detail"
private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

class DetailMovieActivity : AppCompatActivity() {
    private var mContext: Context? = null

    private val animationHelper = AnimationHelper()
    private lateinit var binding: ActivityDetailBinding
    private lateinit var mViewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        mViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        val intent = intent
        val movie = Gson().fromJson(intent.getStringExtra(MOVIE_INTENT_EXTRA), KmmMovie::class.java)

        binding = ActivityDetailBinding.inflate(
            layoutInflater
        )
        binding.movie = movie
        binding.viewModel = mViewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)
        setupToolbar(movie)
        floatingButtonSetup(movie)
        instance!!.dBThread.execute {
            mViewModel.loadFavoriteMovie(
                movie!!.id
            )
        }
    }

    private fun setupToolbar(movie: KmmMovie?) {
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        val toolBarLayout = binding.toolbarLayout
        toolBarLayout.title = movie!!.title
        val appBarLayout = binding.appBar
        appBarLayout.addOnOffsetChangedListener(object : OnOffsetChangedListener {
            var isShow = true
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                val titleTextView = binding.root.findViewById<TextView>(R.id.movie_title_textview)
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    toolBarLayout.title = movie.title
                    // Initially hide the content view.
                    titleTextView.visibility = View.GONE

                    // Retrieve and cache the system's default "short" animation time.
                    animationHelper.goneAnimation(mContext!!, titleTextView)
                    isShow = true
                } else if (isShow) {
                    toolBarLayout.title =
                        " " //careful there should a space between double quote otherwise it wont work
                    animationHelper.visibleAnimation(mContext!!, titleTextView)
                    isShow = false
                }
            }
        })
        val imageURL = IMAGE_BASE_URL + movie.posterPath
        Picasso.get().load(imageURL).placeholder(R.drawable.ic_placeholder)
            .into(binding.headerImageview)
    }

    private fun floatingButtonSetup(movie: KmmMovie?) {
        val fab = binding.fab
        fab.setOnClickListener { view: View ->
            val isFavorite = mViewModel!!.isFavorite.value!!
            mViewModel.isFavorite.value = !isFavorite
            if (isFavorite) {
                view.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(view.context, R.color.red))
                Snackbar.make(view, R.string.favorite_snack_message, Snackbar.LENGTH_LONG).show()
            } else {
                view.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        view.context,
                        android.R.color.darker_gray
                    )
                )
                Snackbar.make(view, R.string.unfavorite_snack_message, Snackbar.LENGTH_LONG).show()
            }
            movie!!.favorite = !isFavorite
            instance!!.dBThread.execute {
                mViewModel.saveFavoriteMovie(
                    movie
                )
            }
        }
    }

    companion object {

        @JvmStatic
        @BindingAdapter("favorite")
        fun setMovieFavorite(view: FloatingActionButton, isFavorite: Boolean) {
            if (isFavorite) {
                view.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(view.context, R.color.red))
            } else {
                view.backgroundTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            view.context,
                            android.R.color.darker_gray
                        )
                    )
            }
        }
    }
}