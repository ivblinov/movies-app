package com.examples.moviesapp.data.mappers

import com.examples.moviesapp.data.models_dto.country_list_dto.CountryObjectDto
import com.examples.moviesapp.data.models_dto.country_list_dto.GenreCountryListDto
import com.examples.moviesapp.data.models_dto.country_list_dto.GenreObjectDto
import com.examples.moviesapp.domain.models.country_list_model.CountryObjectModel
import com.examples.moviesapp.domain.models.country_list_model.GenreCountryListModel
import com.examples.moviesapp.domain.models.country_list_model.GenreObjectModel
import javax.inject.Inject

class MapperGenreCountryListModelFromGenreCountryListDto @Inject constructor() {

    fun transform(
        genreCountryListDto: GenreCountryListDto
    ): GenreCountryListModel {
        val genreCountryListModel = GenreCountryListModel(
            genres = getGenreObjectModelList(genreCountryListDto.genres),
            countries = getCountryObjectModelList(genreCountryListDto.countries),
        )
        return genreCountryListModel
    }

    private fun getGenreObjectModelList(
        genreObjectDtoList: List<GenreObjectDto>
    ): List<GenreObjectModel> {
        val genreObjectsList = mutableListOf<GenreObjectModel>()
        genreObjectDtoList.forEach { genreObjectDto ->
            val genreObjectModel = getGenreObjectModel(genreObjectDto)
            genreObjectsList.add(genreObjectModel)
        }
        return genreObjectsList
    }

    private fun getCountryObjectModelList(
        countryObjectDtoList: List<CountryObjectDto>
    ): List<CountryObjectModel> {
        val countryObjectList = mutableListOf<CountryObjectModel>()
        countryObjectDtoList.forEach { countryObjectDto ->
            val countryObjectModel = getCountryObjectModel(countryObjectDto)
            countryObjectList.add(countryObjectModel)
        }
        return countryObjectList
    }

    private fun getGenreObjectModel(
        genreObjectDto: GenreObjectDto
    ): GenreObjectModel {
        return GenreObjectModel(
            id = genreObjectDto.id,
            genre = genreObjectDto.genre,
        )
    }

    private fun getCountryObjectModel(
        countryObjectDto: CountryObjectDto
    ): CountryObjectModel {
        return CountryObjectModel(
            id = countryObjectDto.id,
            country = countryObjectDto.country,
        )
    }
}