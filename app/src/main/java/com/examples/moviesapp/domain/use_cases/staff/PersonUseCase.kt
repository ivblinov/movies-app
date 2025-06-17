package com.examples.moviesapp.domain.use_cases.staff

import com.examples.moviesapp.data.repositories.StaffRepository
import javax.inject.Inject

class PersonUseCase @Inject constructor(
    private val repository: StaffRepository,
) {

    suspend fun getPerson(id: Int) = repository.getPerson(id)
}