package com.examples.moviesapp.di

import com.examples.moviesapp.presentation.viewmodels.HomePageViewModel
import dagger.Component

@Component
interface AppComponent {

    fun homePageViewModel(): HomePageViewModel
}