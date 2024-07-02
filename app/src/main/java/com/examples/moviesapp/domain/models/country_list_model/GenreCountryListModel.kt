package com.examples.moviesapp.domain.models.country_list_model

import com.examples.moviesapp.entities.country_list.GenreCountryList

data class GenreCountryListModel(
    override val genres: List<GenreObjectModel>,
    override val countries: List<CountryObjectModel>,
) : GenreCountryList