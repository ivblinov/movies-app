package com.examples.moviesapp.domain.models

import com.examples.moviesapp.entities.Person

data class PersonModel(
    override val personId: Int?,
    override val nameRu: String?,
    override val nameEn: String?,
    override val posterUrl: String?,
    override val profession: String?,
    override val films: List<FilmOfActorModel>,
) : Person