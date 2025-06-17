package com.examples.moviesapp.entities

interface BestFilm {
    val kinopoiskId: Int?
    val nameRu: String?
    val nameEn: String?
    val genres: List<Genre>
    val ratingKinopoisk: Float?
    val posterUrl: String?
    val posterUrlPreview: String?
}