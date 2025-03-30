package com.examples.moviesapp.presentation.navigation

interface MovieRouter {

    fun navigateToRoot(destinationId: Int)

    fun clickButtonBack()

    fun navigateToFilm()
}