package com.examples.moviesapp.domain.models.film

import com.examples.moviesapp.entities.film.Image

data class ImageModel(
    override val total: Int?,
    override val totalPages: Int?,
    override val items: List<ImageItemModel>?
) : Image