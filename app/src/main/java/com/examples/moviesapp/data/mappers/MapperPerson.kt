package com.examples.moviesapp.data.mappers

import com.examples.moviesapp.data.mappers.utils.MapperFilmOfActor
import com.examples.moviesapp.data.models_dto.PersonDto
import com.examples.moviesapp.domain.models.PersonModel
import javax.inject.Inject

class MapperPerson @Inject constructor(
    private val mapper: MapperFilmOfActor,
) {

    fun mapToDto(personModel: PersonModel) = with(personModel) {
        PersonDto(
            personId = personId,
            nameRu = nameRu,
            nameEn = nameEn,
            posterUrl = posterUrl,
            profession = profession,
            films = films.map { mapper.mapToDto(it) },
        )
    }

    fun mapFromDto(personDto: PersonDto) = with(personDto) {
        PersonModel(
            personId = personId,
            nameRu = nameRu,
            nameEn = nameEn,
            posterUrl = posterUrl,
            profession = profession,
            films = films.map { mapper.mapFromDto(it) }
        )
    }
}