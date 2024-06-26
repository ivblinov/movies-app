package com.examples.moviesapp.data.data_source

import com.examples.moviesapp.data.api.KinoService
import com.examples.moviesapp.data.models_dto.MovieListDto
import javax.inject.Inject

class MovieListDataSource @Inject constructor(
    private val kinoService: KinoService
) {
    suspend fun loadPremiereList(): MovieListDto {
        return kinoService.getPremieres()
    }
}