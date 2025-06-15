package com.examples.moviesapp.domain.use_cases.staff

import com.examples.moviesapp.data.StaffRepository
import com.examples.moviesapp.domain.models.StaffModel
import javax.inject.Inject

class StaffUseCase @Inject constructor(
    private val repository: StaffRepository,
) {

    suspend fun getCastList(filmId: Int): List<StaffModel> = repository.getCastList(filmId)
}