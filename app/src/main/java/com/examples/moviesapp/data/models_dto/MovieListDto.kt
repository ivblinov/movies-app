package com.examples.moviesapp.data.models_dto

import com.examples.moviesapp.entities.Movie
import com.examples.moviesapp.entities.MovieList
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieListDto(
    @Json(name = "total") override val total: Int,
    @Json(name = "items") override val items: List<Movie>
) : MovieList