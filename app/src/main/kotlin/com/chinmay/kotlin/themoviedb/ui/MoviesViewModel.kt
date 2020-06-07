package com.chinmay.kotlin.themoviedb.ui

import androidx.lifecycle.MutableLiveData
import com.chinmay.kotlin.themoviedb.base.BaseViewModel
import com.chinmay.kotlin.themoviedb.model.Movie

class MoviesViewModel: BaseViewModel() {
    private val moviesTitle = MutableLiveData<String>()
    private val moviesOverview = MutableLiveData<String>()
    private val moviesLanguage = MutableLiveData<String>()

    fun bind(movie: Movie){
        moviesTitle.value = movie.title
        moviesOverview.value = movie.overview
        moviesLanguage.value = movie.original_language
    }

    fun getMoviesTitle():MutableLiveData<String>{
        return moviesTitle
    }

    fun getMoviesOverview():MutableLiveData<String>{
        return moviesOverview
    }

    fun getMoviesLanguage():MutableLiveData<String>{
        return moviesLanguage
    }
}