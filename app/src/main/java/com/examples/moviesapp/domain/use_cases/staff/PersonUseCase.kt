package com.examples.moviesapp.domain.use_cases.staff

import com.examples.moviesapp.data.repositories.FilmRepository
import javax.inject.Inject

class PersonUseCase @Inject constructor(
    private val repository: FilmRepository,
) {

    suspend fun getPerson(id: Int) = repository.getPerson(id)
}