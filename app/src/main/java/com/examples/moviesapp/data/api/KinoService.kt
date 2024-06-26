package com.examples.moviesapp.data.api

import com.examples.moviesapp.data.models_dto.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val API_KEY = "435c8124-4833-40cf-86c2-d4a7ebf4e2dd"

interface KinoService {

    @Headers("X-API-KEY: $API_KEY")
    @GET("/api/v2.2/films/premieres")
    suspend fun getPremieres(
        @Query("year") year: Int = 2024,
        @Query("month") month: String = "JUNE"
    ) : MovieListDto
}