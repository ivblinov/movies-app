package com.examples.moviesapp.domain.models.actor

import com.examples.moviesapp.entities.actor.FilmOfActor

data class FilmOfActorModel(
    override val filmId: Int?,
    override val rating: String?,
    override val professionKey: String?,
) : FilmOfActor