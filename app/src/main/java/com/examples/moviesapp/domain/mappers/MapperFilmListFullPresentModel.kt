package com.examples.moviesapp.domain.mappers

import com.examples.moviesapp.domain.models.FilmListModel
import com.examples.moviesapp.domain.models.country_list_model.CountryObjectModel
import com.examples.moviesapp.domain.models.country_list_model.GenreObjectModel
import com.examples.moviesapp.presentation.presentation_models.FilmListFullPresentModel
import javax.inject.Inject

class MapperFilmListFullPresentModel @Inject constructor() {

    fun transform(
        filmListModel: FilmListModel?,
        country: CountryObjectModel?,
        genre: GenreObjectModel?,
        titleBlock: String?
    ): FilmListFullPresentModel {
        return FilmListFullPresentModel(
            total = filmListModel?.items?.size,
            totalPages = filmListModel?.totalPages,
            items = filmListModel?.items,
            country = country,
            genre = genre,
            titleBlock = titleBlock
        )
    }
}