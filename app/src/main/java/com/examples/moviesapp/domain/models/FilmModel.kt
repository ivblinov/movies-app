package com.examples.moviesapp.domain.models

import com.examples.moviesapp.entities.Film

data class FilmModel(
    override val kinopoiskId: Int?,
    override val imdbId: String?,
    override val nameRu: String?,
    override val nameEn: String?,
    override val nameOriginal: String?,
    override val countries: List<CountryModel>,
    override val genres: List<GenreModel>,
    override val ratingKinopoisk: Float?,
    override val ratingImbd: Float?,
    override val year: Int?,
    override val type: String?,
    override val posterUrl: String?,
    override val posterUrlPreview: String?
) : Film