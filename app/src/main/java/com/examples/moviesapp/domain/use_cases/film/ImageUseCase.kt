package com.examples.moviesapp.domain.use_cases.film

import com.examples.moviesapp.data.repositories.FilmRepository
import javax.inject.Inject

class ImageUseCase @Inject constructor(
    private val repository: FilmRepository,
) {

    suspend fun getImages(filmId: Int) = repository.getImages(filmId)
}