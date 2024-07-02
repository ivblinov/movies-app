package com.examples.moviesapp.data.models_dto.country_list_dto

import com.examples.moviesapp.entities.country_list.GenreObject
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreObjectDto(
    @Json(name = "id") override val id: Int,
    @Json(name = "genre") override val genre: String,
) : GenreObject