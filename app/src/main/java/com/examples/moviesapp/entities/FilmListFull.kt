package com.examples.moviesapp.entities

import com.examples.moviesapp.entities.country_list.CountryObject
import com.examples.moviesapp.entities.country_list.GenreObject

interface FilmListFull {
    val total: Int?
    val totalPages: Int?
    val items: List<Film>?
    val country: CountryObject?
    val genre: GenreObject?
    val titleBlock: String?
}