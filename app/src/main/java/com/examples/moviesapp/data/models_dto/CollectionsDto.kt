package com.examples.moviesapp.data.models_dto

import com.examples.moviesapp.entities.Collections
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CollectionsDto(
    @Json(name = "total") override val total: Int,
    @Json(name = "totalPages") override val totalPages: Int,
    @Json(name = "items") override val items: List<CollectionItemDto>
) : Collections
