package com.examples.moviesapp.data

import com.examples.moviesapp.data.data_source.CollectionsDataSource
import com.examples.moviesapp.data.data_source.MovieListDataSource
import com.examples.moviesapp.data.mappers.MapperMovieListModelFromMovieListDto
import com.examples.moviesapp.domain.models.MovieListModel
import com.examples.moviesapp.entities.Collections
import javax.inject.Inject

class Repository @Inject constructor(
    private val movieListDataSource: MovieListDataSource,
    private val collectionsDataSource: CollectionsDataSource,
    private val mapperMovieModel: MapperMovieListModelFromMovieListDto
) {
    suspend fun loadPremiereList(year: Int, month: String): MovieListModel {
        val movieListDto = movieListDataSource.loadPremiereList(year, month)
        return mapperMovieModel.transform(movieListDto)
    }

    suspend fun loadCollections(): Collections {
        return collectionsDataSource.loadCollections()
    }
}