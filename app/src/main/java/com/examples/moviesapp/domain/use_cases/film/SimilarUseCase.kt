package com.examples.moviesapp.domain.use_cases.film

import com.examples.moviesapp.data.repositories.FilmRepository
import javax.inject.Inject

class SimilarUseCase @Inject constructor(
    private val repository: FilmRepository,
) {

    suspend fun getSimilarList(filmId: Int) = repository.getSimilarList(filmId)
}