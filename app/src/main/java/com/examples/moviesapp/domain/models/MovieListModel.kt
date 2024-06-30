package com.examples.moviesapp.domain.models

import com.examples.moviesapp.entities.MovieList

data class MovieListModel(
    override var total: Int,
    override var items: List<MovieModel>
) : MovieList