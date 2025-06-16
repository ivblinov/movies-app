package com.examples.moviesapp.data.api

import com.examples.moviesapp.data.models_dto.CollectionsDto
import com.examples.moviesapp.data.models_dto.FilmListDto
import com.examples.moviesapp.data.models_dto.country_list_dto.GenreCountryListDto
import com.examples.moviesapp.data.models_dto.MovieListDto
import com.examples.moviesapp.data.models_dto.StaffDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val API_KEY = "435c8124-4833-40cf-86c2-d4a7ebf4e2dd" // для iv-blin@yandex.ru
//private const val API_KEY = "020af2e2-b27e-4a0d-aa54-2e1b66ccb474" // для ivan88blinov@gmail.com

interface KinoService {

    @Headers("X-API-KEY: $API_KEY")
    @GET("/api/v2.2/films/premieres")
    suspend fun getPremieres(
        @Query("year") year: Int,
        @Query("month") month: String
    ): MovieListDto

    @Headers("X-API-KEY: $API_KEY")
    @GET("/api/v2.2/films/collections")
    suspend fun getPopular(
        @Query("type") type: String,
        @Query("page") page: Int
    ): CollectionsDto

    @Headers("X-API-KEY: $API_KEY")
    @GET("/api/v2.2/films")
    suspend fun getFilms(
        @Query("countries") countries: List<Int>,
        @Query("genres") genres: List<Int>,
        @Query("type") type: String
    ): FilmListDto

    @Headers("X-API-KEY: $API_KEY")
    @GET("/api/v2.2/films/filters")
    suspend fun getGenreCountryList(): GenreCountryListDto

    @Headers("X-API-KEY: $API_KEY")
    @GET("/api/v1/staff")
    suspend fun getCastList(
        @Query("filmId") filmId: Int,
    ): List<StaffDto>
}