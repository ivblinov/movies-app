package com.examples.moviesapp.data.models_dto

import com.examples.moviesapp.entities.Staff
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StaffDto(
    @Json(name = "staffId") override val staffId: Int?,
    @Json(name = "nameRu") override val nameRu: String?,
    @Json(name = "nameEn") override val nameEn: String?,
    @Json(name = "description") override val description: String?,
    @Json(name = "posterUrl") override val posterUrl: String?,
    @Json(name = "professionText") override val professionText: String?,
    @Json(name = "professionKey") override val professionKey: String?,
) : Staff