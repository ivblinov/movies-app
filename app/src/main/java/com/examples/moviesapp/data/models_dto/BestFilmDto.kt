package com.examples.moviesapp.data.models_dto

import com.examples.moviesapp.entities.BestFilm
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BestFilmDto(
    @Json(name = "kinopoiskId") override val kinopoiskId: Int?,
    @Json(name = "nameRu") override val nameRu: String?,
    @Json(name = "nameEn") override val nameEn: String?,
    @Json(name = "genres") override val genres: List<GenreDto>,
    @Json(name = "ratingKinopoisk") override val ratingKinopoisk: Float?,
    @Json(name = "posterUrl") override val posterUrl: String?,
    @Json(name = "posterUrlPreview") override val posterUrlPreview: String?
) : BestFilm