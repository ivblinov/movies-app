package com.examples.moviesapp.domain.models

import com.examples.moviesapp.entities.Staff

data class StaffModel(
    override val staffId: Int?,
    override val nameRu: String?,
    override val nameEn: String?,
    override val description: String?,
    override val posterUrl: String?,
    override val professionText: String?,
    override val professionKey: String?,
) : Staff