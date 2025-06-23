package com.examples.moviesapp.entities.film

interface Image {
    val total: Int?
    val totalPages: Int?
    val items: List<ImageItem>?
}