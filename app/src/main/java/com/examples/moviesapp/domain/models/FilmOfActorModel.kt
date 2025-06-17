package com.examples.moviesapp.domain.models

import com.examples.moviesapp.entities.FilmOfActor

data class FilmOfActorModel(
    override val filmId: Int?,
    override val rating: String?,
    override val professionKey: String?,
) : FilmOfActor