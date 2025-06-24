package com.examples.moviesapp.data.models_dto.film

import com.examples.moviesapp.entities.film.SimilarList
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SimilarDtoList(
    @Json(name = "total") override val total: Int?,
    @Json(name = "items") override val items: List<SimilarDto>?,
) : SimilarList