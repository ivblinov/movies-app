package com.examples.moviesapp.domain.models

import android.os.Parcelable
import com.examples.moviesapp.entities.Movie
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    override val kinopoiskId: Int,
    override val nameRu: String,
    override val nameEn: String,
    override val year: Int,
    override val posterUrl: String,
    override val posterUrlPreview: String,
    override val countries: List<CountryModel>,
    override val genres: List<GenreModel>,
    override val duration: Int?,
    override val premiereRu: String
) : Movie, Parcelable