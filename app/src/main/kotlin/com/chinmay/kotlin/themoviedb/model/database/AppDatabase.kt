package com.chinmay.kotlin.themoviedb.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chinmay.kotlin.themoviedb.model.Movie
import com.chinmay.kotlin.themoviedb.model.MovieDao
import com.chinmay.kotlin.themoviedb.utils.GenreConverter

@Database(entities = [Movie::class], version = 1)
@TypeConverters(GenreConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): MovieDao
}