package com.examples.moviesapp.domain

import android.util.Log
import com.examples.moviesapp.data.Repository
import com.examples.moviesapp.domain.models.CollectionsModel
import com.examples.moviesapp.shuffleList
import javax.inject.Inject

private const val TAG = "MyLog"
class LoadCollectionsUseCase @Inject constructor(
    private val repository: Repository
) {
    private val typePopularMovies = "TOP_POPULAR_MOVIES"
    private val typeTop250Movies = "TOP_250_MOVIES"
    private val typeTvSerials = "POPULAR_SERIES"
    private val page = 1

    suspend fun loadPopularMovies(): CollectionsModel? {
        val popularMovies = repository.loadCollections(typePopularMovies, page)
        return shuffleList(popularMovies)
    }

    suspend fun loadTop250Movies(): CollectionsModel? {
        val top250Movies = repository.loadCollections(typeTop250Movies, page)
        return shuffleList(top250Movies)
    }

    suspend fun loadTvSerials(): CollectionsModel? {
        val tvSerials = repository.loadCollections(typeTvSerials, page)
        Log.d(TAG, "loadTvSerials: $tvSerials")
        return shuffleList(tvSerials)
    }
}