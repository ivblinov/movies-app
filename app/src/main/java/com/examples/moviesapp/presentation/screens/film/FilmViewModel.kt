package com.examples.moviesapp.presentation.screens.film

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examples.moviesapp.domain.models.film.FilmInfoModel
import com.examples.moviesapp.domain.models.film.ImageModel
import com.examples.moviesapp.domain.models.film.SimilarModelList
import com.examples.moviesapp.domain.use_cases.film.FilmInfoUseCase
import com.examples.moviesapp.domain.use_cases.film.ImageUseCase
import com.examples.moviesapp.domain.use_cases.film.SimilarUseCase
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

private const val TAG = "MyLog"
class FilmViewModel @Inject constructor(
    private val staffUseCase: StaffUseCase,
    private val filmInfoUseCase: FilmInfoUseCase,
    private val imageUseCase: ImageUseCase,
    private val similarUseCase: SimilarUseCase,
    private val navigator: Navigator,
) : ViewModel() {

    var filmInfo: FilmInfoModel? = null
    var actors: List<Staff> = listOf()
    var images: ImageModel? = null
    var staffList: List<Staff> = listOf()
    var similarList: SimilarModelList? = null

    private val _state = MutableStateFlow<State>(State.Success)
    val state = _state.asStateFlow()

    private val _staffState = MutableStateFlow<State>(State.Success)
    val staffState = _staffState.asStateFlow()

    private val _infoState = MutableStateFlow<State>(State.Success)
    val infoState = _infoState.asStateFlow()

    private val _imageState = MutableStateFlow<State>(State.Success)
    val imageState = _imageState.asStateFlow()

    private val _similarState = MutableStateFlow<State>(State.Success)
    val similarState = _similarState.asStateFlow()

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

    fun getImages(filmId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _imageState.value = State.Loading
            images = imageUseCase.getImages(filmId)
            _imageState.value = State.Success
        }
    }

    fun getSimilarList(filmId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _similarState.value = State.Loading
            similarList = similarUseCase.getSimilarList(filmId)
            _similarState.value = State.Success
        }
    }

    fun navigateToActor(actorId: Int, professionKey: String) {
        navigator.navigateToActor(actorId, professionKey)
    }

    fun navigateToFilm(filmId: Int) {
        navigator.navigateToSimilarFilm(filmId)
    }
}