package com.examples.moviesapp.domain

import com.examples.moviesapp.data.Repository
import com.examples.moviesapp.domain.mappers.MapperFilmListFullPresentModel
import com.examples.moviesapp.domain.models.FilmListModel
import com.examples.moviesapp.domain.models.country_list_model.CountryObjectModel
import com.examples.moviesapp.domain.models.country_list_model.GenreObjectModel
import com.examples.moviesapp.presentation.presentation_models.FilmListFullPresentModel
import javax.inject.Inject

class LoadDynamicSelectionUseCase @Inject constructor(
    private val repository: Repository,
    private val mapperFilmListFullPresentModel: MapperFilmListFullPresentModel
) {
    private val type = "FILM"
    private var countries: List<CountryObjectModel>? = listOf()
    private var genres: List<GenreObjectModel>? = listOf()
    private var country: CountryObjectModel? = null
    private var genre: GenreObjectModel? = null
    private var filmList: FilmListModel? = null

    private suspend fun loadGenreCountryList() {
        val genreCountryList = repository.loadGenreCountyList()
        countries = genreCountryList.countries
        genres = genreCountryList.genres
    }

    private fun getRandomListsOfGenreCountry() {
        country = countries?.randomOrNull()
        genre = genres?.randomOrNull()
    }

    suspend fun loadDynamicSelectionFilms(): FilmListFullPresentModel {
        if (country == null) {
            loadGenreCountryList()
        }
        do {
            getRandomListsOfGenreCountry()
            filmList =
                repository.loadFilmList(listOf(country?.id ?: 1), listOf(genre?.id ?: 1), type)
        } while (
            filmList?.total == 0
        )
        return mapperFilmListFullPresentModel.transform(
            filmListModel = filmList,
            country = country,
            genre = genre
        )
    }
}