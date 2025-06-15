package com.examples.moviesapp.presentation.screens.film

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examples.moviesapp.domain.use_cases.staff.StaffUseCase
import com.examples.moviesapp.entities.Staff
import com.examples.moviesapp.presentation.states.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MyLog"
class FilmViewModel @Inject constructor(
    private val staffUseCase: StaffUseCase,
) : ViewModel() {

    var actors: List<Staff> = listOf()

    private val _state = MutableStateFlow<State>(State.Success)
    val state = _state.asStateFlow()

    fun getCastList(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = State.Loading
            actors = staffUseCase.getCastList(movieId)
            _state.value = State.Success
        }
    }
}