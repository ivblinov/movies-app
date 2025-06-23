package com.examples.moviesapp.data.models_dto.film

import com.examples.moviesapp.entities.film.Image
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageDto(
    @Json(name = "total") override val total: Int?,
    @Json(name = "totalPages") override val totalPages: Int?,
    @Json(name = "items") override val items: List<ImageItemDto>?
) : Image