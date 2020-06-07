package com.chinmay.kotlin.themoviedb.injection

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.chinmay.kotlin.themoviedb.model.database.AppDatabase
import com.chinmay.kotlin.themoviedb.ui.post.MoviesListViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "movies").build()
            @Suppress("UNCHECKED_CAST")
            return MoviesListViewModel(db.moviesDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}