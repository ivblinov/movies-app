package com.examples.moviesapp.data

import com.examples.moviesapp.data.data_source.CollectionsDataSource
import com.examples.moviesapp.data.data_source.FilmListDataSource
import com.examples.moviesapp.data.data_source.MovieListDataSource
import com.examples.moviesapp.data.mappers.MapperCollectionsModelFromCollectionsDto
import com.examples.moviesapp.data.mappers.MapperFilmListModelFromFilmListDto
import com.examples.moviesapp.data.mappers.MapperGenreCountryListModelFromGenreCountryListDto
import com.examples.moviesapp.data.mappers.MapperMovieListModelFromMovieListDto
import com.examples.moviesapp.domain.models.CollectionsModel
import com.examples.moviesapp.domain.models.FilmListModel
import com.examples.moviesapp.domain.models.MovieListModel
import com.examples.moviesapp.domain.models.country_list_model.GenreCountryListModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val movieListDataSource: MovieListDataSource,
    private val collectionsDataSource: CollectionsDataSource,
    private val filmListDataSource: FilmListDataSource,
    private val mapperMovieModel: MapperMovieListModelFromMovieListDto,
    private val mapperCollectionsModel: MapperCollectionsModelFromCollectionsDto,
    private val mapperFilmListModel: MapperFilmListModelFromFilmListDto,
    private val mapperGenreCountryListModel: MapperGenreCountryListModelFromGenreCountryListDto,
) {
    suspend fun loadPremiereList(year: Int, month: String): MovieListModel {
        val movieListDto = movieListDataSource.loadPremiereList(year, month)
        return mapperMovieModel.transform(movieListDto)
    }

    suspend fun loadCollections(type: String, page: Int): CollectionsModel {
        val collections = collectionsDataSource.loadCollections(type, page)
        return mapperCollectionsModel.transform(collections)
    }

    suspend fun loadFilmList(countries: List<Int>, genres: List<Int>, type: String): FilmListModel {
        val filmListDto = filmListDataSource.loadFilmList(countries, genres, type)
        return mapperFilmListModel.transform(filmListDto)
    }

    suspend fun loadGenreCountyList(): GenreCountryListModel {
        val genreCountryList = filmListDataSource.loadGenreCountyList()
        return mapperGenreCountryListModel.transform(genreCountryList)
    }
}