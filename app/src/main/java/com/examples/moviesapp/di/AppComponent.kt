package com.examples.moviesapp.di

import android.app.Application
import com.examples.moviesapp.di.modules.AppModule
import com.examples.moviesapp.di.modules.NavigationModule
import com.examples.moviesapp.di.modules.NetworkModule
import com.examples.moviesapp.presentation.MainActivity
import com.examples.moviesapp.presentation.screens.film.FilmFragment
import com.examples.moviesapp.presentation.screens.home.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        NavigationModule::class,
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)

    fun inject(homeFragment: HomeFragment)

    fun inject(filmFragment: FilmFragment)
}