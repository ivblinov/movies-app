package com.examples.moviesapp.data.models_dto.film

import com.examples.moviesapp.data.models_dto.CountryDto
import com.examples.moviesapp.data.models_dto.GenreDto
import com.examples.moviesapp.entities.film.FilmInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmInfoDto(
    @Json(name = "kinopoiskId") override val kinopoiskId: Int?,
    @Json(name = "posterUrl") override val posterUrl: String?,
    @Json(name = "posterUrlPreview") override val posterUrlPreview: String?,
    @Json(name = "ratingKinopoisk") override val ratingKinopoisk: Float?,
    @Json(name = "nameRu") override val nameRu: String?,
    @Json(name = "nameEn") override val nameEn: String?,
    @Json(name = "year") override val year: Int?,
    @Json(name = "genres") override val genres: List<GenreDto>?,
    @Json(name = "countries") override val countries: List<CountryDto>?,
    @Json(name = "filmLength") override val filmLength: Int?,
    @Json(name = "ratingAgeLimits") override val ratingAgeLimits: String?,
    @Json(name = "shortDescription") override val shortDescription: String?,
    @Json(name = "description") override val description: String?,
    @Json(name = "type") override val type: String?,
    @Json(name = "serial") override val serial: Boolean?,
    @Json(name = "completed") override val completed: Boolean?,
) : FilmInfo