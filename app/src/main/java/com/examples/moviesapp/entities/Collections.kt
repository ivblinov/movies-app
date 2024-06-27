package com.examples.moviesapp.entities

interface Collections {
    val total: Int
    val totalPages: Int
    val items: List<CollectionItem>
}