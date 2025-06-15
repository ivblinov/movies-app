package com.examples.moviesapp.domain.models

import android.os.Parcelable
import com.examples.moviesapp.entities.Genre
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenreModel(
    override val genre: String
) : Genre, Parcelable