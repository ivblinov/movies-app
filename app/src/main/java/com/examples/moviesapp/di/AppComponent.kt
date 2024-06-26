package com.examples.moviesapp.di

import com.examples.moviesapp.presentation.fragments.HomePageFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class
    ]
)
interface AppComponent {

    fun inject(fragment: HomePageFragment)
}