package com.examples.moviesapp.data.mappers.film

import com.examples.moviesapp.data.models_dto.film.SimilarDtoList
import com.examples.moviesapp.domain.models.film.SimilarModelList
import javax.inject.Inject

class MapperSimilarList @Inject constructor(
    private val mapper: MapperSimilar,
) {

    fun mapFromDto(similarDtoList: SimilarDtoList): SimilarModelList = with(similarDtoList) {
        SimilarModelList(
            total = total,
            items = items?.map { mapper.mapFromDto(it) }
        )
    }
}