package com.examples.moviesapp.entities

interface Person {
    val personId: Int?
    val nameRu: String?
    val nameEn: String?
    val posterUrl: String?
    val profession: String?
    val films: List<FilmOfActor>
}