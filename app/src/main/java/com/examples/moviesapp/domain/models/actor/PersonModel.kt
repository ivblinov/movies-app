package com.examples.moviesapp.domain.models.actor

import com.examples.moviesapp.entities.actor.Person

data class PersonModel(
    override val personId: Int?,
    override val nameRu: String?,
    override val nameEn: String?,
    override val posterUrl: String?,
    override val profession: String?,
    override val films: List<FilmOfActorModel>,
) : Person