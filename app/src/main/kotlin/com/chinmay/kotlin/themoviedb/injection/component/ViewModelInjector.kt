package com.chinmay.kotlin.themoviedb.injection.component

import dagger.Component
import com.chinmay.kotlin.themoviedb.injection.module.NetworkModule
import com.chinmay.kotlin.themoviedb.ui.post.MoviesListViewModel
import com.chinmay.kotlin.themoviedb.ui.post.MoviesViewModel
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified MoviesListViewModel.
     * @param moviesListViewModel MoviesListViewModel in which to inject the dependencies
     */
    fun inject(moviesListViewModel: MoviesListViewModel)
    /**
     * Injects required dependencies into the specified PostViewModel.
     * @param moviesViewModel MoviesViewModel in which to inject the dependencies
     */
    fun inject(moviesViewModel: MoviesViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}