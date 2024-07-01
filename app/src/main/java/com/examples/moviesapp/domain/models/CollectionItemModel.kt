package com.examples.moviesapp.domain.models

import com.examples.moviesapp.entities.CollectionItem

data class CollectionItemModel(
    override val kinopoiskId: Int?,
    override val nameRu: String?,
    override val nameEn: String?,
    override val nameOriginal: String?,
    override val countries: List<CountryModel>,
    override val genres: List<GenreModel>,
    override val ratingKinopoisk: Float?,
    override val ratingImbd: Float?,
    override val year: String?,
    override val type: String?,
    override val posterUrl: String?,
    override val posterUrlPreview: String?
) : CollectionItem