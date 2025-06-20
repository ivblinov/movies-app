package com.examples.moviesapp.domain.use_cases.film

import com.examples.moviesapp.data.repositories.FilmRepository
import javax.inject.Inject

class FilmInfoUseCase @Inject constructor(
    private val repository: FilmRepository,
) {

    suspend fun getFilmInfo(filmId: Int) = repository.getFilmInfo(filmId)
}