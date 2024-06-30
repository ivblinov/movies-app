package com.examples.moviesapp.domain.models

import com.examples.moviesapp.entities.Movie

data class MovieModel(
    override val kinopoiskId: Int,
    override val nameRu: String,
    override val nameEn: String,
    override val year: Int,
    override val posterUrl: String,
    override val posterUrlPreview: String,
    override val countries: List<CountryModel>,
    override val genres: List<GenreModel>,
    override val duration: Int?,
    override val premiereRu: String
) : Movie