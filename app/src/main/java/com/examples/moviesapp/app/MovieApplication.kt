package com.examples.moviesapp.app

import android.app.Application
import com.examples.moviesapp.di.AppComponent
import com.examples.moviesapp.di.DaggerAppComponent

class MovieApplication : Application() {

    val appComponent: AppComponent = DaggerAppComponent.create()
}