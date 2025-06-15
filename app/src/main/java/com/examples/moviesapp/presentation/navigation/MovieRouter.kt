package com.examples.moviesapp.presentation.navigation

import com.examples.moviesapp.domain.models.MovieModel

interface MovieRouter {

    fun navigateToRoot(destinationId: Int)

    fun clickButtonBack()

    fun navigateToFilm(movie: MovieModel)
}