package com.examples.moviesapp.domain.models.film

import com.examples.moviesapp.entities.film.Staff

data class StaffModel(
    override val staffId: Int?,
    override val nameRu: String?,
    override val nameEn: String?,
    override val description: String?,
    override val posterUrl: String?,
    override val professionText: String?,
    override val professionKey: String?,
) : Staff