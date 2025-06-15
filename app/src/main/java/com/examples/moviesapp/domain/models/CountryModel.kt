package com.examples.moviesapp.domain.models

import android.os.Parcelable
import com.examples.moviesapp.entities.Country
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryModel(
    override val country: String
) : Country, Parcelable