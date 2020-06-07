package com.chinmay.kotlin.themoviedb.model

data class Results(val results: List<Movie>,
                   val page: Int,
                   val total_results: Int,
                   val total_pages: Int,
                   val dates: Dates)
data class Dates(val maximum: String, val minimum: String)
