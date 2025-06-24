package com.examples.moviesapp.domain.models.film

import com.examples.moviesapp.entities.film.SimilarList

data class SimilarModelList(
    override val total: Int?,
    override val items: List<SimilarModel>?,
) : SimilarList