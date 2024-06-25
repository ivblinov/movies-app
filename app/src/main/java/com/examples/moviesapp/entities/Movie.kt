package com.examples.moviesapp.entities

interface Movie {
    val kinopoiskId: Int
    val nameRu: String
    val nameEn: String
    val year: Int
    val posterUrl: String
    val posterUrlPreview: String
    val countries: List<Country>
    val genres: List<Genre>
    val duration: Int?
    val premiereRu: String
}