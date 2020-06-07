package com.chinmay.kotlin.themoviedb.base

import androidx.lifecycle.ViewModel
import com.chinmay.kotlin.themoviedb.injection.component.DaggerViewModelInjector
import com.chinmay.kotlin.themoviedb.injection.component.ViewModelInjector
import com.chinmay.kotlin.themoviedb.injection.module.NetworkModule
import com.chinmay.kotlin.themoviedb.ui.MoviesListViewModel
import com.chinmay.kotlin.themoviedb.ui.MoviesViewModel

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is MoviesListViewModel -> injector.inject(this)
            is MoviesViewModel -> injector.inject(this)
        }
    }
}