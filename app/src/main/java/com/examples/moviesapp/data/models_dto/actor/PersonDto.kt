package com.examples.moviesapp.data.models_dto.actor

import com.examples.moviesapp.entities.actor.Person
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonDto(
    @Json(name = "personId") override val personId: Int?,
    @Json(name = "nameRu") override val nameRu: String?,
    @Json(name = "nameEn") override val nameEn: String?,
    @Json(name = "posterUrl") override val posterUrl: String?,
    @Json(name = "profession") override val profession: String?,
    @Json(name = "films") override val films: List<FilmOfActorDto>,
) : Person