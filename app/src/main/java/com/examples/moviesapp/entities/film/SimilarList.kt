package com.examples.moviesapp.entities.film

interface SimilarList {
    val total: Int?
    val items: List<Similar>?
}