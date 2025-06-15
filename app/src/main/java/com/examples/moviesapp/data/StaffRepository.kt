package com.examples.moviesapp.data

import com.examples.moviesapp.data.data_source.StaffDataSource
import com.examples.moviesapp.data.mappers.MapperStaff
import com.examples.moviesapp.domain.models.StaffModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StaffRepository @Inject constructor(
    private val staffDataSource: StaffDataSource,
    private val mapper: MapperStaff,
) {

    suspend fun getCastList(filmId: Int): List<StaffModel> =
        staffDataSource.getCastList(filmId).map {
            mapper.mapFromDto(it)
        }.filter { it.professionKey == ACTOR_KEY }

    companion object {

        private const val ACTOR_KEY = "ACTOR"
    }
}