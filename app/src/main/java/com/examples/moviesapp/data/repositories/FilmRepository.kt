package com.examples.moviesapp.data.repositories

import com.examples.moviesapp.data.data_source.FilmDataSource
import com.examples.moviesapp.data.mappers.actor.MapperPerson
import com.examples.moviesapp.data.mappers.film.MapperFilmInfo
import com.examples.moviesapp.data.mappers.film.MapperImage
import com.examples.moviesapp.data.mappers.film.MapperSimilarList
import com.examples.moviesapp.data.mappers.film.MapperStaff
import com.examples.moviesapp.domain.models.actor.PersonModel
import com.examples.moviesapp.domain.models.film.FilmInfoModel
import com.examples.moviesapp.domain.models.film.ImageModel
import com.examples.moviesapp.domain.models.film.SimilarModelList
import com.examples.moviesapp.domain.models.film.StaffModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilmRepository @Inject constructor(
    private val filmDataSource: FilmDataSource,
    private val mapper: MapperStaff,
    private val personMapper: MapperPerson,
    private val filmInfoMapper: MapperFilmInfo,
    private val imageMapper: MapperImage,
    private val similarMapper: MapperSimilarList,
) {

    suspend fun getCastList(filmId: Int): List<StaffModel> =
        filmDataSource.getCastList(filmId).map {
            mapper.mapFromDto(it)
        }

    suspend fun getPerson(id: Int): PersonModel =
        personMapper.mapFromDto(filmDataSource.getPerson(id))

    suspend fun getFilmInfo(filmId: Int): FilmInfoModel =
        filmInfoMapper.mapFromDto(filmDataSource.getFilmInfo(filmId))

    suspend fun getImages(filmId: Int): ImageModel =
        imageMapper.mapFromDto(filmDataSource.getImages(filmId))

    suspend fun getSimilarList(filmId: Int): SimilarModelList =
        similarMapper.mapFromDto(filmDataSource.getSimilarList(filmId))
}