package com.examples.moviesapp.data.data_source

import com.examples.moviesapp.data.api.KinoService
import javax.inject.Inject

class BestFilmDataSource @Inject constructor(
    private val kinoService: KinoService,
) {

    suspend fun getBestFilm(id: Int) = kinoService.getBestFilm(id)
}