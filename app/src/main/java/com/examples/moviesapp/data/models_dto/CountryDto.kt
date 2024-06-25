package com.examples.moviesapp.data.models_dto

import com.examples.moviesapp.entities.Country
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CountryDto(
    @Json(name = "country") override val country: String
) : Country