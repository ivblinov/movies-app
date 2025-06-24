package com.examples.moviesapp.entities.film

interface Similar {
    val filmId: Int?
    val nameRu: String?
    val nameEn: String?
    val posterUrl: String?
    val posterUrlPreview: String?
}