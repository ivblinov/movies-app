package com.examples.moviesapp.data.data_source

import com.examples.moviesapp.data.api.RetrofitInstance
import com.examples.moviesapp.data.models_dto.MovieListDto
import javax.inject.Inject

class MovieListDataSource @Inject constructor() {

    suspend fun loadPremiereList(): MovieListDto {
        return RetrofitInstance.kinoApi.getPremieres()
    }
}