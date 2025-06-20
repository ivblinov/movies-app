package com.examples.moviesapp.entities.film

import com.examples.moviesapp.entities.Country
import com.examples.moviesapp.entities.Genre

interface FilmInfo {
    val kinopoiskId: Int?
    val posterUrl: String?          // выбрать какой постер превью или просто
    val posterUrlPreview: String?   // Если разницы нет, то превью, т.к. он легче вроде
    val ratingKinopoisk: Float?
    val nameRu: String?
    val nameEn: String?
    val year: Int?
    val genres: List<Genre>?
    val countries: List<Country>?
    val filmLength: Int?
    val ratingAgeLimits: String?
    val shortDescription: String?
    val description: String?
    val type: String?               // если сериал, то немного другой ui
    val serial: Boolean?            // потестить. если хватит только типа, то удалить
    val completed: Boolean?         // потестить. удалить
}