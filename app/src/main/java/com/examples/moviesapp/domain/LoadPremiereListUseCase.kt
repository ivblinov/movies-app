package com.examples.moviesapp.domain

import com.examples.moviesapp.data.Repository
import com.examples.moviesapp.data.models_dto.MovieListDto

class LoadPremiereListUseCase(
    private val repository: Repository
) {
    suspend fun loadPremiereList(): MovieListDto {
        return repository.loadPremiereList()
    }
}