package com.examples.moviesapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examples.moviesapp.domain.LoadPremiereListUseCase
import com.examples.moviesapp.domain.models.MovieListModel
import com.examples.moviesapp.domain.use_cases.LoadCollectionsUseCase
import com.examples.moviesapp.domain.use_cases.LoadDynamicSelection2UseCase
import com.examples.moviesapp.domain.use_cases.LoadDynamicSelectionUseCase
import com.examples.moviesapp.entities.Collections
import com.examples.moviesapp.entities.FilmListFull
import com.examples.moviesapp.presentation.navigation.Navigator
import com.examples.moviesapp.presentation.states.AllButtonState
import com.examples.moviesapp.presentation.states.HomePageState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val loadPremiereListUseCase: LoadPremiereListUseCase,
    private val loadCollectionsUseCase: LoadCollectionsUseCase,
    private val loadDynamicSelectionUseCase: LoadDynamicSelectionUseCase,
    private val loadDynamicSelection2UseCase: LoadDynamicSelection2UseCase,
    private val navigator: Navigator,
) : ViewModel() {

    var premiereList: MovieListModel? = null
    var popularFilms: Collections? = null
    var top250Films: Collections? = null
    var dynamicSelectionFilms: FilmListFull? = null
    var dynamicSelectionFilms2: FilmListFull? = null
    var tvSerialsList: Collections? = null

    var premiereScrollPosition = 0
    var premiereScrollOffset = 0

    private val _premiereState = MutableStateFlow<HomePageState>(HomePageState.Success)
    val premiereState = _premiereState.asStateFlow()

    private val _allPremiereState = MutableStateFlow<AllButtonState>(AllButtonState.Gone)
    val allPremiereState = _allPremiereState.asStateFlow()

    private val _popularState = MutableStateFlow<HomePageState>(HomePageState.Success)
    val popularState = _popularState.asStateFlow()

    private val _allPopularState = MutableStateFlow<AllButtonState>(AllButtonState.Gone)
    val allPopularState = _allPopularState.asStateFlow()

    private val _top250State = MutableStateFlow<HomePageState>(HomePageState.Success)
    val top250State = _top250State.asStateFlow()

    private val _allTop250State = MutableStateFlow<AllButtonState>(AllButtonState.Gone)
    val allTop250State = _allTop250State.asStateFlow()

    private val _dynamicSelectionState = MutableStateFlow<HomePageState>(HomePageState.Success)
    val dynamicSelectionState = _dynamicSelectionState.asStateFlow()

    private val _allDynamicSelectionState = MutableStateFlow<AllButtonState>(AllButtonState.Gone)
    val allDynamicSelectionState = _allDynamicSelectionState.asStateFlow()

    private val _dynamicSelectionState2 = MutableStateFlow<HomePageState>(HomePageState.Success)
    val dynamicSelectionState2 = _dynamicSelectionState2.asStateFlow()

    private val _allDynamicSelectionState2 = MutableStateFlow<AllButtonState>(AllButtonState.Gone)
    val allDynamicSelectionState2 = _allDynamicSelectionState2.asStateFlow()

    private val _tvSerialsState = MutableStateFlow<HomePageState>(HomePageState.Success)
    val tvSerialsState = _tvSerialsState.asStateFlow()

    private val _allSerialsState = MutableStateFlow<AllButtonState>(AllButtonState.Gone)
    val allSerialsState = _allSerialsState.asStateFlow()

    init {
        loadPremiere()
        loadPopular()
        loadTop250()
        loadDynamicSelection()
        loadDynamicSelection2()
        loadTvSerials()
    }

    private fun loadPremiere() {
        viewModelScope.launch {
            _premiereState.value = HomePageState.Loading
            premiereList = loadPremiereListUseCase.getPremiereList()
            premiereList?.let { checkMovieListSize(_allPremiereState, it.total) }
            _premiereState.value = HomePageState.Success
        }
    }

    private fun loadPopular() {
        viewModelScope.launch {
            _popularState.value = HomePageState.Loading
            popularFilms = loadCollectionsUseCase.loadPopularMovies()
            popularFilms?.let { checkMovieListSize(_allPopularState, it.total) }
            _popularState.value = HomePageState.Success
        }
    }

    private fun loadTop250() {
        viewModelScope.launch {
            _top250State.value = HomePageState.Loading
            top250Films = loadCollectionsUseCase.loadTop250Movies()
            top250Films?.let { checkMovieListSize(_allTop250State, it.total) }
            _top250State.value = HomePageState.Success
        }
    }

    private fun loadDynamicSelection() {
        viewModelScope.launch {
            _dynamicSelectionState.value = HomePageState.Loading
            dynamicSelectionFilms = loadDynamicSelectionUseCase.loadDynamicSelectionFilms()
            dynamicSelectionFilms?.let { checkMovieListSize(_allDynamicSelectionState, it.total) }
            _dynamicSelectionState.value = HomePageState.Success
        }
    }

    private fun loadDynamicSelection2() {
        viewModelScope.launch {
            _dynamicSelectionState2.value = HomePageState.Loading
            dynamicSelectionFilms2 = loadDynamicSelection2UseCase.loadDynamicSelectionFilms()
            dynamicSelectionFilms2?.let { checkMovieListSize(_allDynamicSelectionState2, it.total) }
            _dynamicSelectionState2.value = HomePageState.Success
        }
    }

    private fun loadTvSerials() {
        viewModelScope.launch {
            _tvSerialsState.value = HomePageState.Loading
            tvSerialsList = loadCollectionsUseCase.loadTvSerials()
            tvSerialsList?.let { checkMovieListSize(_allSerialsState, it.total) }
            _tvSerialsState.value = HomePageState.Success
        }
    }

    private fun checkMovieListSize(
        state: MutableStateFlow<AllButtonState>,
        listSize: Int?, size: Int = 20
    ) {
        if (listSize != null && listSize > size) {
            state.value = AllButtonState.Visible
        }
    }

    fun navigateToFilm(filmId: Int) {
        navigator.navigateToFilm(filmId)
    }

    fun navigateToListFilm() {
        navigator.navigateToListFilm()
    }
}