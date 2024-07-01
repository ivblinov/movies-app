package com.examples.moviesapp.data.mappers.utils

import com.examples.moviesapp.data.models_dto.GenreDto
import com.examples.moviesapp.domain.models.GenreModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapperGenreModel @Inject constructor() {

    fun getGenreModelListFromGenreDtoList(
        genreDtoList: List<GenreDto>
    ): List<GenreModel> {
        val genresList = mutableListOf<GenreModel>()
        genreDtoList.forEach { genreDto ->
            val genreModel = getGenreModelFromGenreDto(genreDto)
            genresList.add(genreModel)
        }
        return genresList.toList()
    }

    private fun getGenreModelFromGenreDto(
        genreDto: GenreDto
    ): GenreModel {
        return GenreModel(
            genre = genreDto.genre
        )
    }
}