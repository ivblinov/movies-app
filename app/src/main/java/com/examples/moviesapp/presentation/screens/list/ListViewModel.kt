package com.examples.moviesapp.presentation.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examples.moviesapp.domain.LoadPremiereListUseCase
import com.examples.moviesapp.domain.models.MovieListModel
import com.examples.moviesapp.presentation.navigation.Navigator
import com.examples.moviesapp.presentation.states.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val premierUseCase: LoadPremiereListUseCase,
    private val navigator: Navigator,
) : ViewModel() {

    var premierList: MovieListModel? = null

    private val _state = MutableStateFlow<State>(State.Success)
    val state = _state.asStateFlow()

    init {
        loadPremierList()
    }

    fun loadPremierList() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = State.Loading
            premierList = premierUseCase.getPremiereList()
            _state.value = State.Success
        }
    }

    fun navigateToFilm(filmId: Int) {
        navigator.navigateToFilmFromList(filmId)
    }
}