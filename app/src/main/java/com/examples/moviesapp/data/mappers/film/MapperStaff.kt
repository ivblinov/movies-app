package com.examples.moviesapp.data.mappers.film

import com.examples.moviesapp.data.models_dto.film.StaffDto
import com.examples.moviesapp.domain.models.film.StaffModel
import javax.inject.Inject

class MapperStaff @Inject constructor() {

    fun mapToDto(staffModel: StaffModel) = with(staffModel) {
        StaffDto(
            staffId = staffId,
            nameRu = nameRu,
            nameEn = nameEn,
            description = description,
            posterUrl = posterUrl,
            professionText = professionText,
            professionKey = professionKey,
        )
    }

    fun mapFromDto(staffDto: StaffDto) = with(staffDto) {
        StaffModel(
            staffId = staffId,
            nameRu = nameRu,
            nameEn = nameEn,
            description = description,
            posterUrl = posterUrl,
            professionText = professionText,
            professionKey = professionKey,
        )
    }
}