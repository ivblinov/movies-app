package com.examples.moviesapp.data.mappers.film

import com.examples.moviesapp.data.mappers.utils.MapperCountryModelList
import com.examples.moviesapp.data.mappers.utils.MapperGenreModel
import com.examples.moviesapp.data.models_dto.film.FilmInfoDto
import com.examples.moviesapp.domain.models.film.FilmInfoModel
import javax.inject.Inject

class MapperFilmInfo @Inject constructor(
    private val mapperGenre: MapperGenreModel,
    private val mapperCountry: MapperCountryModelList,
) {

    fun mapFromDto(filmDto: FilmInfoDto): FilmInfoModel = with(filmDto) {
        FilmInfoModel(
            kinopoiskId = kinopoiskId,
            posterUrl = posterUrl,
            posterUrlPreview = posterUrlPreview,
            ratingKinopoisk = ratingKinopoisk,
            nameRu = nameRu,
            nameEn = nameEn,
            year = year,
            genres = genres?.let { mapperGenre.getGenreModelListFromGenreDtoList(it) },
            countries = countries?.let { mapperCountry.getCountryModelListFromCountryDtoList(it) },
            filmLength = filmLength,
            ratingAgeLimits = ratingAgeLimits,
            shortDescription = shortDescription,
            description = description,
            type = type,
            serial = serial,
            completed = completed,
        )
    }
}