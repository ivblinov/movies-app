package com.examples.moviesapp.data.mappers

import com.examples.moviesapp.data.mappers.utils.MapperCountryModelList
import com.examples.moviesapp.data.mappers.utils.MapperGenreModel
import com.examples.moviesapp.data.models_dto.MovieDto
import com.examples.moviesapp.data.models_dto.MovieListDto
import com.examples.moviesapp.domain.models.MovieListModel
import com.examples.moviesapp.domain.models.MovieModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapperMovieListModelFromMovieListDto @Inject constructor(
    private val countryModelList: MapperCountryModelList,
    private val genreModelList: MapperGenreModel
) {

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
                countries = countryModelList.getCountryModelListFromCountryDtoList(movieDto.countries),
                genres = genreModelList.getGenreModelListFromGenreDtoList(movieDto.genres),
                duration = movieDto.duration,
                premiereRu = movieDto.premiereRu
            )
            movieModelList.add(movieModel)
        }
        return movieModelList.toList()
    }
}