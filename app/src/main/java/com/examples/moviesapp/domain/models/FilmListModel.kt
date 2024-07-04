package com.examples.moviesapp.domain.models

import com.examples.moviesapp.entities.FilmList

data class FilmListModel(
    override var total: Int,
    override var totalPages: Int,
    override var items: List<FilmModel>
) : FilmList