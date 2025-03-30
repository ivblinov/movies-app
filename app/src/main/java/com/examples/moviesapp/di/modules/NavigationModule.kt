package com.examples.moviesapp.di.modules

import com.examples.moviesapp.presentation.navigation.MovieRouter
import com.examples.moviesapp.presentation.navigation.Navigator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {

    @Singleton
    @Provides
    fun provideNavigator() = Navigator()

    @Singleton
    @Provides
    fun provideMovieRouter(navigator: Navigator): MovieRouter = navigator
}