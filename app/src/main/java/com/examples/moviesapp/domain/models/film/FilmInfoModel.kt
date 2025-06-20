package com.examples.moviesapp.domain.models.film

import com.examples.moviesapp.domain.models.CountryModel
import com.examples.moviesapp.domain.models.GenreModel
import com.examples.moviesapp.entities.film.FilmInfo

data class FilmInfoModel(
    override val kinopoiskId: Int?,
    override val posterUrl: String?,
    override val posterUrlPreview: String?,
    override val ratingKinopoisk: Float?,
    override val nameRu: String?,
    override val nameEn: String?,
    override val year: Int?,
    override val genres: List<GenreModel>?,
    override val countries: List<CountryModel>?,
    override val filmLength: Int?,
    override val ratingAgeLimits: String?,
    override val shortDescription: String?,
    override val description: String?,
    override val type: String?,
    override val serial: Boolean?,
    override val completed: Boolean?,
) : FilmInfo