package com.examples.moviesapp.data.mappers.film

import com.examples.moviesapp.data.models_dto.film.ImageDto
import com.examples.moviesapp.domain.models.film.ImageModel
import javax.inject.Inject

class MapperImage @Inject constructor(
    private val mapper: MapperImageItem,
) {

    fun mapFromDto(imageDto: ImageDto): ImageModel = with(imageDto) {
        ImageModel(
            total = total,
            totalPages = totalPages,
            items = items?.map { mapper.mapFromDto(it) }
        )
    }
}