package com.examples.moviesapp.domain.models

import com.examples.moviesapp.entities.BestFilm
import com.examples.moviesapp.entities.Genre

data class BestFilmModel(
    override val kinopoiskId: Int?,
    override val nameRu: String?,
    override val nameEn: String?,
    override val genres: List<Genre>,
    override val ratingKinopoisk: Float?,
    override val posterUrl: String?,
    override val posterUrlPreview: String?,
) : BestFilm