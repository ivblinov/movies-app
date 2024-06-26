package com.examples.moviesapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examples.moviesapp.data.Repository
import com.examples.moviesapp.data.api.RetrofitInstance
import com.examples.moviesapp.domain.LoadPremiereListUseCase
import com.examples.moviesapp.entities.MovieList
import com.examples.moviesapp.presentation.states.HomePageState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "MyLog"
class HomePageViewModel(
    private val loadPremiereListUseCase: LoadPremiereListUseCase
) : ViewModel() {

    var movieList: MovieList? = null

    private val _state = MutableStateFlow<HomePageState>(HomePageState.Success)
    val state = _state.asStateFlow()

    init {
        loadPremiere()
    }

    fun loadPremiere() {
        viewModelScope.launch {
            _state.value = HomePageState.Loading
            movieList = loadPremiereListUseCase.loadPremiereList()
            _state.value = HomePageState.Success
        }
    }
}