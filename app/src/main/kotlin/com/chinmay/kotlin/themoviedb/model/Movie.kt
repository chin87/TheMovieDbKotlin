package com.chinmay.kotlin.themoviedb.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Movie(
        val vote_count: Long,
        @field:PrimaryKey
        val id: Long,
        val video: Boolean,
        val vote_average: Double,
        val title: String,
        val popularity: Double,
        val poster_path: String,
        val original_language: String,
        val original_title: String,
        val genre_ids: List<Int>,
        val backdrop_path: String? = null,
        val adult: Boolean,
        val overview: String,
        val release_date: String
)

//data class Genre(val id: Int, val name: String)
