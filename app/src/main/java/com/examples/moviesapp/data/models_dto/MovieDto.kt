package com.examples.moviesapp.data.models_dto

import com.examples.moviesapp.entities.Country
import com.examples.moviesapp.entities.Genre
import com.examples.moviesapp.entities.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDto(
    @Json(name = "kinopoiskId") override val kinopoiskId: Int,
    @Json(name = "nameRu") override val nameRu: String,
    @Json(name = "nameEn") override val nameEn: String,
    @Json(name = "year") override val year: Int,
    @Json(name = "posterUrl") override val posterUrl: String,
    @Json(name = "posterUrlPreview") override val posterUrlPreview: String,
    @Json(name = "countries") override val countries: List<Country>,
    @Json(name = "genres") override val genres: List<Genre>,
    @Json(name = "duration") override val duration: Int?,
    @Json(name = "premiereRu") override val premiereRu: String
) : Movie