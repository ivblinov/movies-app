package com.examples.moviesapp.domain.models

import com.examples.moviesapp.entities.FilmList

data class FilmListModel(
    override val total: Int,
    override val totalPages: Int,
    override val items: List<FilmModel>
) : FilmList