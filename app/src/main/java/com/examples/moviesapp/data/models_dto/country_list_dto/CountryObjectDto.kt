package com.examples.moviesapp.data.models_dto.country_list_dto

import com.examples.moviesapp.entities.country_list.CountryObject
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryObjectDto(
    @Json(name = "id") override val id: Int,
    @Json(name = "country") override val country: String,
) : CountryObject