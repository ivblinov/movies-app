package com.examples.moviesapp.data.data_source

import com.examples.moviesapp.data.api.KinoService
import com.examples.moviesapp.data.models_dto.FilmListDto
import com.examples.moviesapp.data.models_dto.country_list_dto.GenreCountryListDto
import javax.inject.Inject

class FilmListDataSource @Inject constructor(
    private val kinoService: KinoService
) {
    suspend fun loadFilmList(countries: List<Int>, genres: List<Int>, type: String): FilmListDto {
        return kinoService.getFilms(countries, genres, type)
    }

    suspend fun loadGenreCountyList(): GenreCountryListDto {
        return kinoService.getGenreCountryList()
    }
}