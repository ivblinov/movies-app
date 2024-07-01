package com.examples.moviesapp.data.mappers.utils

import com.examples.moviesapp.data.models_dto.CountryDto
import com.examples.moviesapp.domain.models.CountryModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapperCountryModelList @Inject constructor() {

    fun getCountryModelListFromCountryDtoList(
        countryDtoList: List<CountryDto>
    ): List<CountryModel> {
        val countriesList = mutableListOf<CountryModel>()
        countryDtoList.forEach { countryDto ->
            val countryModel = getCountryModelFromCountryDto(countryDto)
            countriesList.add(countryModel)
        }
        return countriesList.toList()
    }

    private fun getCountryModelFromCountryDto(
        countryDto: CountryDto
    ): CountryModel {
        return CountryModel(
            country = countryDto.country
        )
    }
}