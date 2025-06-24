package com.examples.moviesapp.data.mappers.film

import com.examples.moviesapp.data.models_dto.film.SimilarDto
import com.examples.moviesapp.domain.models.film.SimilarModel
import javax.inject.Inject

class MapperSimilar @Inject constructor() {

    fun mapFromDto(similarDto: SimilarDto): SimilarModel = with(similarDto) {
        SimilarModel(
            filmId = filmId,
            nameRu = nameRu,
            nameEn = nameEn,
            posterUrl = posterUrl,
            posterUrlPreview = posterUrlPreview,
        )
    }
}