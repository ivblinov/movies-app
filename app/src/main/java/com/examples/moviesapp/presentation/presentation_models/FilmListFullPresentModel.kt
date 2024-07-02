package com.examples.moviesapp.presentation.presentation_models

import com.examples.moviesapp.domain.models.FilmModel
import com.examples.moviesapp.domain.models.country_list_model.CountryObjectModel
import com.examples.moviesapp.domain.models.country_list_model.GenreObjectModel
import com.examples.moviesapp.entities.FilmListFull

data class FilmListFullPresentModel(
    override val total: Int?,
    override val totalPages: Int?,
    override val items: List<FilmModel>?,
    override val country: CountryObjectModel?,
    override val genre: GenreObjectModel?
) : FilmListFull