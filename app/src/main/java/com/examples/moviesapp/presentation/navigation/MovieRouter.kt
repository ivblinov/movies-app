package com.examples.moviesapp.presentation.navigation

interface MovieRouter {

    fun navigateToRoot(destinationId: Int)

    fun clickButtonBack()

    fun navigateToFilm(filmId: Int)

    fun navigateToBestFilm(filmId: Int)

    fun navigateToActor(actorId: Int, professionKey: String)

    fun navigateToSimilarFilm(filmId: Int)
}