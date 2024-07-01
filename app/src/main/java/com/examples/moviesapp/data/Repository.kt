package com.examples.moviesapp.data

import com.examples.moviesapp.data.data_source.CollectionsDataSource
import com.examples.moviesapp.data.data_source.MovieListDataSource
import com.examples.moviesapp.data.mappers.MapperCollectionsModelFromCollectionsDto
import com.examples.moviesapp.data.mappers.MapperMovieListModelFromMovieListDto
import com.examples.moviesapp.domain.models.CollectionsModel
import com.examples.moviesapp.domain.models.MovieListModel
import javax.inject.Inject

class Repository @Inject constructor(
    private val movieListDataSource: MovieListDataSource,
    private val collectionsDataSource: CollectionsDataSource,
    private val mapperMovieModel: MapperMovieListModelFromMovieListDto,
    private val mapperCollectionsModel: MapperCollectionsModelFromCollectionsDto
) {
    suspend fun loadPremiereList(year: Int, month: String): MovieListModel {
        val movieListDto = movieListDataSource.loadPremiereList(year, month)
        return mapperMovieModel.transform(movieListDto)
    }

    suspend fun loadCollections(type: String, page: Int): CollectionsModel {
        val collections = collectionsDataSource.loadCollections(type, page)
        return mapperCollectionsModel.transform(collections)
    }
}