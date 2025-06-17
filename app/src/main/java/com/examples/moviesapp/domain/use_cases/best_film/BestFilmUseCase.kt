package com.examples.moviesapp.domain.use_cases.best_film

import com.examples.moviesapp.data.repositories.BestFilmRepository
import javax.inject.Inject

class BestFilmUseCase @Inject constructor(
    private val repository: BestFilmRepository,
) {

    suspend fun getBestFilm(id: Int) = repository.getBestFilm(id)
}