package com.examples.moviesapp.data.mappers

import com.examples.moviesapp.data.models_dto.CountryDto
import com.examples.moviesapp.data.models_dto.GenreDto
import com.examples.moviesapp.data.models_dto.MovieDto
import com.examples.moviesapp.data.models_dto.MovieListDto
import com.examples.moviesapp.domain.models.CountryModel
import com.examples.moviesapp.domain.models.GenreModel
import com.examples.moviesapp.domain.models.MovieListModel
import com.examples.moviesapp.domain.models.MovieModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapperMovieListModelFromMovieListDto @Inject constructor() {

    fun transform(movieListDto: MovieListDto): MovieListModel {
        return MovieListModel(
            total = movieListDto.total,
            items = getMovieModelListFromMovieDtoList(movieListDto.items)
        )
    }

    private fun getMovieModelListFromMovieDtoList(
        movieDtoList: List<MovieDto>
    ): List<MovieModel> {
        val movieModelList = mutableListOf<MovieModel>()
        movieDtoList.forEach { movieDto ->
            val movieModel = MovieModel(
                kinopoiskId = movieDto.kinopoiskId,
                nameRu = movieDto.nameRu,
                nameEn = movieDto.nameEn,
                year = movieDto.year,
                posterUrl = movieDto.posterUrl,
                posterUrlPreview = movieDto.posterUrl,
                countries = getCountryModelListFromCountryDtoList(movieDto.countries),
                genres = getGenreModelListFromGenreDtoList(movieDto.genres),
                duration = movieDto.duration,
                premiereRu = movieDto.premiereRu
            )
            movieModelList.add(movieModel)
        }
        return movieModelList.toList()
    }

    private fun getCountryModelListFromCountryDtoList(
        countryDtoList: List<CountryDto>
    ): List<CountryModel> {
        val countriesList = mutableListOf<CountryModel>()
        countryDtoList.forEach { countryDto ->
            val countryModel = getCountryModelFromCountryDto(countryDto)
            countriesList.add(countryModel)
        }
        return countriesList.toList()
    }

    private fun getGenreModelListFromGenreDtoList(
        genreDtoList: List<GenreDto>
    ): List<GenreModel> {
        val genresList = mutableListOf<GenreModel>()
        genreDtoList.forEach { genreDto ->
            val genreModel = getGenreModelFromGenreDto(genreDto)
            genresList.add(genreModel)
        }
        return genresList.toList()
    }

    private fun getCountryModelFromCountryDto(
        countryDto: CountryDto
    ): CountryModel {
        return CountryModel(
            country = countryDto.country
        )
    }

    private fun getGenreModelFromGenreDto(
        genreDto: GenreDto
    ): GenreModel {
        return GenreModel(
            genre = genreDto.genre
        )
    }
}