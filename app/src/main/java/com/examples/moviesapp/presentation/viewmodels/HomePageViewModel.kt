package com.examples.moviesapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examples.moviesapp.domain.LoadCollectionsUseCase
import com.examples.moviesapp.domain.LoadPremiereListUseCase
import com.examples.moviesapp.entities.Collections
import com.examples.moviesapp.entities.MovieList
import com.examples.moviesapp.presentation.states.AllButtonState
import com.examples.moviesapp.presentation.states.HomePageState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomePageViewModel @Inject constructor(
    private val loadPremiereListUseCase: LoadPremiereListUseCase,
    private val loadCollectionsUseCase: LoadCollectionsUseCase
) : ViewModel() {

    var premiereList: MovieList? = null
    var popularFilms: Collections? = null

    private val _premiereState = MutableStateFlow<HomePageState>(HomePageState.Success)
    val premiereState = _premiereState.asStateFlow()

    private val _allPremiereState = MutableStateFlow<AllButtonState>(AllButtonState.Gone)
    val allPremiereState = _allPremiereState.asStateFlow()

    private val _popularState = MutableStateFlow<HomePageState>(HomePageState.Success)
    val popularState = _popularState.asStateFlow()

    private val _allPopularState = MutableStateFlow<AllButtonState>(AllButtonState.Gone)
    val allPopularState = _allPopularState.asStateFlow()

    init {
        loadPremiere()
        loadPopular()
    }

    private fun loadPremiere() {
        viewModelScope.launch {
            _premiereState.value = HomePageState.Loading
            premiereList = loadPremiereListUseCase.loadPremiereList()
            premiereList?.let { checkMovieListSize(_allPremiereState, it.total) }
            _premiereState.value = HomePageState.Success
        }
    }

    private fun loadPopular() {
        viewModelScope.launch {
            _popularState.value = HomePageState.Loading
            popularFilms = loadCollectionsUseCase.loadCollections()
            popularFilms?.let { checkMovieListSize(_allPopularState, it.total) }
            _popularState.value = HomePageState.Success
        }
    }

    fun checkMovieListSize(
        state: MutableStateFlow<AllButtonState>,
        listSize: Int, size: Int = 20
    ) {
        if (listSize > size) {
            state.value = AllButtonState.Visible
        }
    }
}