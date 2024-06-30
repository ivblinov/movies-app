package com.examples.moviesapp.domain.models

import com.examples.moviesapp.entities.Genre

data class GenreModel(
    override val genre: String
) : Genre