package com.examples.moviesapp.data.data_source

import com.examples.moviesapp.data.api.KinoService
import com.examples.moviesapp.data.models_dto.StaffDto
import javax.inject.Inject

class StaffDataSource @Inject constructor(
    private val kinoService: KinoService,
) {

    suspend fun getCastList(filmId: Int): List<StaffDto> = kinoService.getCastList(filmId)
}