package com.examples.moviesapp.domain.models.film

import com.examples.moviesapp.entities.film.ImageItem

data class ImageItemModel(
    override val imageUrl: String?,
    override val previewUrl: String?,
) : ImageItem