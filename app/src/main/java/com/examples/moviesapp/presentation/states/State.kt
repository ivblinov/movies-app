package com.examples.moviesapp.presentation.states

sealed class State {
    data object Success : State()
    data object Loading : State()
}