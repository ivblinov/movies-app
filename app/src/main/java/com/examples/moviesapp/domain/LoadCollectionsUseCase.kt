package com.examples.moviesapp.domain

import com.examples.moviesapp.data.Repository
import com.examples.moviesapp.entities.Collections
import javax.inject.Inject

class LoadCollectionsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun loadCollections(): Collections {
        return repository.loadCollections()
    }
}