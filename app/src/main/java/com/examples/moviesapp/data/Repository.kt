package com.examples.moviesapp.data

import com.examples.moviesapp.data.data_source.MovieListDataSource

class Repository(
    private val movieListDataSource: MovieListDataSource
) {
    fun getPremiereList() {}
}