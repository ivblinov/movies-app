package com.examples.moviesapp.presentation.screens.film

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examples.moviesapp.domain.models.film.FilmInfoModel
import com.examples.moviesapp.domain.use_cases.film.FilmInfoUseCase
import com.examples.moviesapp.domain.use_cases.staff.StaffUseCase
import com.examples.moviesapp.entities.film.Staff
import com.examples.moviesapp.presentation.navigation.Navigator
import com.examples.moviesapp.presentation.screens.actor.ACTOR
import com.examples.moviesapp.presentation.states.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FilmViewModel @Inject constructor(
    private val staffUseCase: StaffUseCase,
    private val filmInfoUseCase: FilmInfoUseCase,
    private val navigator: Navigator,
) : ViewModel() {

    var filmInfo: FilmInfoModel? = null
    var actors: List<Staff> = listOf()
    var staffList: List<Staff> = listOf()

    private val _state = MutableStateFlow<State>(State.Success)
    val state = _state.asStateFlow()

    private val _staffState = MutableStateFlow<State>(State.Success)
    val staffState = _staffState.asStateFlow()

    private val _infoState = MutableStateFlow<State>(State.Success)
    val infoState = _infoState.asStateFlow()

    fun getFilmInfo(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _infoState.value = State.Loading
            filmInfo = filmInfoUseCase.getFilmInfo(movieId)
            _infoState.value = State.Success
        }
    }

    fun getCastList(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = State.Loading
            _staffState.value = State.Loading

            val allStaff = staffUseCase.getCastList(movieId)
            actors = allStaff.filter { it.professionKey == ACTOR }
            staffList = allStaff.filterNot { it.professionKey == ACTOR }

            _state.value = State.Success
            _staffState.value = State.Success
        }
    }

    fun navigateToActor(actorId: Int, professionKey: String) {
        navigator.navigateToActor(actorId, professionKey)
    }
}