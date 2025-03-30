package com.examples.moviesapp.utils

import android.content.Context
import com.examples.moviesapp.app.MovieApplication
import com.examples.moviesapp.di.AppComponent

fun Context.appComponent(): AppComponent {
    return when (this) {
        is MovieApplication -> appComponent
        else -> this.applicationContext.appComponent()
    }
}