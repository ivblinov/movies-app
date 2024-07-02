package com.examples.moviesapp.entities

interface FilmList {
    val total: Int
    val totalPages: Int
    val items: List<Film>
}