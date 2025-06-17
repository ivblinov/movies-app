package com.examples.moviesapp.data.mappers

import com.examples.moviesapp.data.mappers.utils.MapperGenreModel
import com.examples.moviesapp.data.models_dto.BestFilmDto
import com.examples.moviesapp.domain.models.BestFilmModel
import javax.inject.Inject

class MapperBestFilm @Inject constructor(
    private val mapper: MapperGenreModel
) {

    fun mapFromDto(bestFilmDto: BestFilmDto): BestFilmModel = with(bestFilmDto) {
        BestFilmModel(
            kinopoiskId = kinopoiskId,
            nameRu = nameRu,
            nameEn = nameEn,
            genres = mapper.getGenreModelListFromGenreDtoList(genres),
            ratingKinopoisk = ratingKinopoisk,
            posterUrl = posterUrl,
            posterUrlPreview = posterUrlPreview,
        )
    }
}