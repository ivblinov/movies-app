package com.examples.moviesapp.domain.use_cases.staff

import com.examples.moviesapp.data.repositories.FilmRepository
import com.examples.moviesapp.domain.models.film.StaffModel
import javax.inject.Inject

class StaffUseCase @Inject constructor(
    private val repository: FilmRepository,
) {

    suspend fun getCastList(filmId: Int): List<StaffModel> = repository.getCastList(filmId)
}