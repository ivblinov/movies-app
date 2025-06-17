package com.examples.moviesapp.data.mappers.utils

import com.examples.moviesapp.data.models_dto.FilmOfActorDto
import com.examples.moviesapp.domain.models.FilmOfActorModel
import javax.inject.Inject

class MapperFilmOfActor @Inject constructor() {

    fun mapToDto(filmModel: FilmOfActorModel): FilmOfActorDto = with(filmModel) {
        FilmOfActorDto(
            filmId = filmId,
            rating = rating,
            professionKey = professionKey,
        )
    }

    fun mapFromDto(filmDto: FilmOfActorDto): FilmOfActorModel = with(filmDto) {
        FilmOfActorModel(
            filmId = filmId,
            rating = rating,
            professionKey = professionKey,
        )
    }
}