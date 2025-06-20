package com.examples.moviesapp.data.data_source

import com.examples.moviesapp.data.api.KinoService
import com.examples.moviesapp.data.models_dto.actor.PersonDto
import com.examples.moviesapp.data.models_dto.film.StaffDto
import javax.inject.Inject

class FilmDataSource @Inject constructor(
    private val kinoService: KinoService,
) {

    suspend fun getCastList(filmId: Int): List<StaffDto> = kinoService.getCastList(filmId)

    suspend fun getPerson(id: Int): PersonDto = kinoService.getPerson(id)

    suspend fun getFilmInfo(filmId: Int) = kinoService.getFilmInfo(filmId)
}