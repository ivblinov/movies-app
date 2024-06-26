package com.examples.moviesapp.data.data_source

import com.examples.moviesapp.data.api.KinoService
import com.examples.moviesapp.data.models_dto.CollectionsDto
import javax.inject.Inject

class CollectionsDataSource @Inject constructor(
    private val kinoService: KinoService
) {
    suspend fun loadCollections(type: String, page: Int): CollectionsDto {
        return kinoService.getPopular(type, page)
    }
}