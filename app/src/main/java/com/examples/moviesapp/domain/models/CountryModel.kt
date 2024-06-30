package com.examples.moviesapp.domain.models

import com.examples.moviesapp.entities.Country

data class CountryModel(
    override val country: String
) : Country