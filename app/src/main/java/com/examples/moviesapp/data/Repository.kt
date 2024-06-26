package com.examples.moviesapp.data

import com.examples.moviesapp.data.data_source.MovieListDataSource
import com.examples.moviesapp.data.models_dto.MovieListDto

class Repository(
    private val movieListDataSource: MovieListDataSource
) {
    suspend fun loadPremiereList(): MovieListDto {
        return movieListDataSource.loadPremiereList()
    }
}