package com.examples.moviesapp.data.models_dto

import com.examples.moviesapp.entities.Film
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmDto(
    @Json(name = "kinopoiskId") override val kinopoiskId: Int?,
    @Json(name = "imdbId") override val imdbId: String?,
    @Json(name = "nameRu") override val nameRu: String?,
    @Json(name = "nameEn") override val nameEn: String?,
    @Json(name = "nameOriginal") override val nameOriginal: String?,
    @Json(name = "countries") override val countries: List<CountryDto>,
    @Json(name = "genres") override val genres: List<GenreDto>,
    @Json(name = "ratingKinopoisk") override val ratingKinopoisk: Float?,
    @Json(name = "ratingImbd") override val ratingImbd: Float?,
    @Json(name = "year") override val year: Int?,
    @Json(name = "type") override val type: String?,
    @Json(name = "posterUrl") override val posterUrl: String?,
    @Json(name = "posterUrlPreview") override val posterUrlPreview: String?
) : Film