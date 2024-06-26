package com.examples.moviesapp.domain

import com.examples.moviesapp.data.Repository
import com.examples.moviesapp.entities.MovieList
import javax.inject.Inject

class LoadPremiereListUseCase @Inject constructor (
    private val repository: Repository
) {
    suspend fun loadPremiereList(): MovieList {
        return repository.loadPremiereList()
    }
}