package com.examples.moviesapp.data.models_dto.film

import com.examples.moviesapp.entities.film.ImageItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageItemDto(
    @Json(name = "imageUrl") override val imageUrl: String?,
    @Json(name = "previewUrl") override val previewUrl: String?,
) : ImageItem