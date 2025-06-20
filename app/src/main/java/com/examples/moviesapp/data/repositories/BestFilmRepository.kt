package com.examples.moviesapp.data.repositories

import com.examples.moviesapp.data.data_source.BestFilmDataSource
import com.examples.moviesapp.data.mappers.actor.MapperBestFilm
import com.examples.moviesapp.domain.models.actor.BestFilmModel
import javax.inject.Inject

class BestFilmRepository @Inject constructor(
    private val localSource: BestFilmDataSource,
    private val mapper: MapperBestFilm,
) {

    suspend fun getBestFilm(id: Int): BestFilmModel = mapper.mapFromDto(localSource.getBestFilm(id))
}