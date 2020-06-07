package com.chinmay.kotlin.themoviedb.ui.post

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chinmay.kotlin.themoviedb.R
import com.chinmay.kotlin.themoviedb.base.BaseViewModel
import com.chinmay.kotlin.themoviedb.model.Movie
import com.chinmay.kotlin.themoviedb.model.MovieDao
import com.chinmay.kotlin.themoviedb.model.Results
import com.chinmay.kotlin.themoviedb.network.TheMovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MoviesListViewModel(private val movieDao: MovieDao) : BaseViewModel() {
    @Inject
    lateinit var theMovieApi: TheMovieApi
    val moviesListAdapter: MoviesListAdapter = MoviesListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadMovies() }

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            onRetrieveMoviesListStart()
            withContext(Dispatchers.IO) {
                if (movieDao.all.isEmpty()) {
                    val apiCall = theMovieApi.getMovies()
                    apiCall.enqueue(object : Callback<Results> {

                        override fun onFailure(call: Call<Results>, t: Throwable) {
                            onRetrieveMoviesListError()
                        }

                        override fun onResponse(call: Call<Results>, response: Response<Results>) {
                            if (response.isSuccessful) {
                                val list = response?.body()?.results ?: emptyList()
                                saveToDb(list)
                                onRetrieveMoviesListSuccess(list)
                            } else {
                                onRetrieveMoviesListError()
                            }
                        }
                    })
                } else {
                    val list = movieDao.all
                    withContext(Dispatchers.Main) {
                        onRetrieveMoviesListSuccess(list)
                    }
                }
            }
        }
    }

    private fun onRetrieveMoviesListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveMoviesListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun saveToDb(movieList: List<Movie>) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDao.insertAll(*movieList.toTypedArray())
        }
    }

    private fun onRetrieveMoviesListSuccess(movieList: List<Movie>) {
        onRetrieveMoviesListFinish()
        moviesListAdapter.updateMoviesList(movieList)
    }

    private fun onRetrieveMoviesListError() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                onRetrieveMoviesListFinish()
                errorMessage.value = R.string.post_error
            }
        }
    }
}