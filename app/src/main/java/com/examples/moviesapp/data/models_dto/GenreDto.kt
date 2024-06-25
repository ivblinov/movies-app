package com.examples.moviesapp.data.models_dto

import com.examples.moviesapp.entities.Genre
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreDto(
    @Json(name = "genre") override val genre: String
) : Genre