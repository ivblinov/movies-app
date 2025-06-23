package com.examples.moviesapp.data.mappers.film

import com.examples.moviesapp.data.models_dto.film.ImageItemDto
import com.examples.moviesapp.domain.models.film.ImageItemModel
import javax.inject.Inject

class MapperImageItem @Inject constructor() {

    fun mapFromDto(imageItemDto: ImageItemDto): ImageItemModel = with(imageItemDto) {
        ImageItemModel(
            imageUrl = imageUrl,
            previewUrl = previewUrl,
        )
    }
}