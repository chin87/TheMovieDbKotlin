package com.chinmay.kotlin.themoviedb.ui.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.chinmay.kotlin.themoviedb.R
import com.chinmay.kotlin.themoviedb.databinding.ItemMoviesBinding
import com.chinmay.kotlin.themoviedb.model.Movie

class MoviesListAdapter : RecyclerView.Adapter<MoviesListAdapter.ViewHolder>() {
    private lateinit var movieList: List<Movie>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMoviesBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                        R.layout.item_movies, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return if (::movieList.isInitialized) movieList.size else 0
    }

    fun updateMoviesList(movieList: List<Movie>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = MoviesViewModel()

        fun bind(movie: Movie) {
            viewModel.bind(movie)
            binding.viewModel = viewModel
        }
    }
}