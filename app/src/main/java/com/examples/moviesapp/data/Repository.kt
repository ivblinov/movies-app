package com.examples.moviesapp.data

import com.examples.moviesapp.data.data_source.CollectionsDataSource
import com.examples.moviesapp.data.data_source.MovieListDataSource
import com.examples.moviesapp.entities.Collections
import com.examples.moviesapp.entities.MovieList
import javax.inject.Inject

class Repository @Inject constructor(
    private val movieListDataSource: MovieListDataSource,
    private val collectionsDataSource: CollectionsDataSource
) {
    suspend fun loadPremiereList(): MovieList {
        return movieListDataSource.loadPremiereList()
    }

    suspend fun loadCollections(): Collections {
        return collectionsDataSource.loadCollections()
    }
}