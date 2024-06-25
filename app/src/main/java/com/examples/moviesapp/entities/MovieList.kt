package com.examples.moviesapp.entities

interface MovieList {
    val total: Int
    val items: List<Movie>
}