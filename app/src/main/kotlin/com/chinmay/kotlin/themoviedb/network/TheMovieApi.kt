package com.chinmay.kotlin.themoviedb.network

import com.chinmay.kotlin.themoviedb.model.Results
import retrofit2.Call
import retrofit2.http.GET

interface TheMovieApi {
    /**
     * Get the list of the movies from the API
     */
    @GET("upcoming?api_key=b7cd3340a794e5a2f35e3abb820b497f")
    fun getMovies(): Call<Results>
}