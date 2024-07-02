package com.examples.moviesapp.data.models_dto.country_list_dto

import com.examples.moviesapp.entities.country_list.GenreCountryList
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreCountryListDto(
    @Json(name = "genres") override val genres: List<GenreObjectDto>,
    @Json(name = "countries") override val countries: List<CountryObjectDto>,
) : GenreCountryList