package com.examples.moviesapp.data.models_dto

import com.examples.moviesapp.entities.FilmOfActor
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmOfActorDto(
    @Json(name = "filmId") override val filmId: Int?,
    @Json(name = "rating") override val rating: String?,
    @Json(name = "professionKey") override val professionKey: String?,
) : FilmOfActor