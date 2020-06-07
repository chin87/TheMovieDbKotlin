package com.chinmay.kotlin.themoviedb.ui

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chinmay.kotlin.themoviedb.R
import com.chinmay.kotlin.themoviedb.databinding.ActivityMoviesListBinding
import com.chinmay.kotlin.themoviedb.injection.ViewModelFactory
import com.google.android.material.snackbar.Snackbar


class MoviesListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoviesListBinding
    private lateinit var viewModel: MoviesListViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movies_list)
        binding.moviesList.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val dividerItemDecoration =
                DividerItemDecoration(binding.moviesList.context,DividerItemDecoration.VERTICAL)
        binding.moviesList.addItemDecoration(dividerItemDecoration)

        viewModel =
                ViewModelProviders
                        .of(this, ViewModelFactory(this))
                        .get(MoviesListViewModel::class.java)
        viewModel.errorMessage
                .observe(this,
                        Observer { errorMessage ->
                            if (errorMessage != null) showError(errorMessage) else hideError()
                        })
        binding.viewModel = viewModel
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}