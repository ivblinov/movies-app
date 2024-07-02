package com.examples.moviesapp.domain.models.country_list_model

import com.examples.moviesapp.entities.country_list.CountryObject

data class CountryObjectModel(
    override val id: Int,
    override val country: String,
) : CountryObject