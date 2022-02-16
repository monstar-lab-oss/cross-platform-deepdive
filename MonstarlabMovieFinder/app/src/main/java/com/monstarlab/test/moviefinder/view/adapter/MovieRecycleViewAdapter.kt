package com.monstarlab.test.moviefinder.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.monstarlab.test.moviefinder.R
import com.monstarlab.test.moviefinder.databinding.LayoutMoviePlaceholderBinding
import com.monstarlab.test.moviefinder.db.entity.Movie
import com.monstarlab.test.moviefinder.kmp.shared.data.KmmMovie
import com.monstarlab.test.moviefinder.view.MainActivity
import com.monstarlab.test.moviefinder.view.adapter.MovieRecycleViewAdapter.MovieViewHolder
import com.monstarlab.test.moviefinder.viewmodel.MainViewModel
import com.squareup.picasso.Picasso

/**
 * [RecyclerView.Adapter] that can display a [Movie].
 */
class MovieRecycleViewAdapter(context: MainActivity, values: List<KmmMovie>) :
    RecyclerView.Adapter<MovieViewHolder>() {
    private var mValues: List<KmmMovie>
    private val mViewModel: MainViewModel
    private val mContext: Context
    var listener: OnNavigationListener? = null

    init {
        mContext = context
        mViewModel = ViewModelProvider(context).get(MainViewModel::class.java)
        mValues = values
    }

    interface OnNavigationListener {
        fun onItemClick(view: View?, movie: KmmMovie?, position: Int)
    }

    fun setValues(values: List<KmmMovie>) {
        mValues = values
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: LayoutMoviePlaceholderBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.layout_movie_placeholder,
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(mContext, mViewModel, mValues[position], position)
        if (position >= mValues.size - 1 && position <= mViewModel.totalPages.value!!) {
            val nextPage = mViewModel.pageIndex.value!! + 1
            mViewModel.pageIndex.value = nextPage
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class MovieViewHolder(private val mBinding: LayoutMoviePlaceholderBinding) :
        RecyclerView.ViewHolder(
            mBinding.root
        ) {
        fun bind(context: Context?, viewModel: MainViewModel?, movie: KmmMovie?, position: Int) {
            mBinding.context = context
            mBinding.viewModel = viewModel
            mBinding.movie = movie
            mBinding.position = position
            mBinding.executePendingBindings()
        }

        init {
            mBinding.movieImage.setOnClickListener { v: View ->
                if (listener != null) {
                    listener!!.onItemClick(v, mBinding.movie, mBinding.position!!)
                }
            }
        }
    }

    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

        @JvmStatic
        @BindingAdapter("movie")
        fun setupMovieImage(view: ImageView?, movie: KmmMovie) {
            val imageURL = IMAGE_BASE_URL + movie.posterPath
            Picasso.get().load(imageURL).placeholder(R.drawable.ic_placeholder).into(view)
        }
    }


}