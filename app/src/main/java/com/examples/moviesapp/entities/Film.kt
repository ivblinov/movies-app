package com.examples.moviesapp.entities

interface Film {
    val kinopoiskId: Int?
    val imdbId: String?
    val nameRu: String?
    val nameEn: String?
    val nameOriginal: String?
    val countries: List<Country>
    val genres: List<Genre>
    val ratingKinopoisk: Float?
    val ratingImbd: Float?
    val year: Int?
    val type: String?
    val posterUrl: String?
    val posterUrlPreview: String?
}