package com.examples.moviesapp.data.repositories

import com.examples.moviesapp.data.data_source.StaffDataSource
import com.examples.moviesapp.data.mappers.MapperPerson
import com.examples.moviesapp.data.mappers.MapperStaff
import com.examples.moviesapp.domain.models.PersonModel
import com.examples.moviesapp.domain.models.StaffModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StaffRepository @Inject constructor(
    private val staffDataSource: StaffDataSource,
    private val mapper: MapperStaff,
    private val personMapper: MapperPerson,
) {

    suspend fun getCastList(filmId: Int): List<StaffModel> =
        staffDataSource.getCastList(filmId).map {
            mapper.mapFromDto(it)
        }.filter { it.professionKey == ACTOR_KEY }

    suspend fun getPerson(id: Int): PersonModel =
        personMapper.mapFromDto(staffDataSource.getPerson(id))

    companion object {

        private const val ACTOR_KEY = "ACTOR"
    }
}