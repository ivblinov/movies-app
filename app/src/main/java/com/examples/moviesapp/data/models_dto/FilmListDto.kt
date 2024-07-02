package com.examples.moviesapp.data.models_dto

import com.examples.moviesapp.entities.FilmList
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmListDto(
    @Json(name = "total") override val total: Int,
    @Json(name = "totalPages") override val totalPages: Int,
    @Json(name = "items") override val items: List<FilmDto>
) : FilmList