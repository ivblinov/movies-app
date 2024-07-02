package com.examples.moviesapp.data.mappers

import com.examples.moviesapp.data.mappers.utils.MapperCountryModelList
import com.examples.moviesapp.data.mappers.utils.MapperGenreModel
import com.examples.moviesapp.data.models_dto.FilmDto
import com.examples.moviesapp.data.models_dto.FilmListDto
import com.examples.moviesapp.domain.models.FilmListModel
import com.examples.moviesapp.domain.models.FilmModel
import javax.inject.Inject

class MapperFilmListModelFromFilmListDto @Inject constructor(
    private val countryModelList: MapperCountryModelList,
    private val genreModelList: MapperGenreModel
) {
    fun transform(filmListDto: FilmListDto): FilmListModel {
        return FilmListModel(
            total = filmListDto.total,
            totalPages = filmListDto.totalPages,
            items = getFilmListModelFromFilmListDto(filmListDto.items)
        )
    }

    private fun getFilmListModelFromFilmListDto(
        filmDtoList: List<FilmDto>
    ): List<FilmModel> {
        val filmModelList = mutableListOf<FilmModel>()
        filmDtoList.forEach { filmDto ->
            val filmModel = FilmModel(
                kinopoiskId = filmDto.kinopoiskId,
                imdbId = filmDto.imdbId,
                nameRu = filmDto.nameRu,
                nameEn = filmDto.nameEn,
                nameOriginal = filmDto.nameOriginal,
                countries = countryModelList.getCountryModelListFromCountryDtoList(filmDto.countries),
                genres = genreModelList.getGenreModelListFromGenreDtoList(filmDto.genres),
                ratingKinopoisk = filmDto.ratingKinopoisk,
                ratingImbd = filmDto.ratingImbd,
                year = filmDto.year,
                type = filmDto.type,
                posterUrl = filmDto.posterUrl,
                posterUrlPreview = filmDto.posterUrlPreview
            )
            filmModelList.add(filmModel)
        }
        return filmModelList.toList()
    }
}