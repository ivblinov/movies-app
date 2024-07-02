package com.examples.moviesapp.domain.models.country_list_model

import com.examples.moviesapp.entities.country_list.GenreObject

data class GenreObjectModel(
    override val id: Int,
    override val genre: String,
) : GenreObject