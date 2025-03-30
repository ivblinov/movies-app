package com.examples.moviesapp.app

import android.app.Application
import com.examples.moviesapp.di.AppComponent
import com.examples.moviesapp.di.DaggerAppComponent

class MovieApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}