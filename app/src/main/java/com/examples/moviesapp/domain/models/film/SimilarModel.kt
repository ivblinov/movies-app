package com.examples.moviesapp.domain.models.film

import com.examples.moviesapp.entities.film.Similar

data class SimilarModel(
    override val filmId: Int?,
    override val nameRu: String?,
    override val nameEn: String?,
    override val posterUrl: String?,
    override val posterUrlPreview: String?,
) : Similar