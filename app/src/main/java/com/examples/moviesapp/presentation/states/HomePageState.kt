package com.examples.moviesapp.presentation.states

sealed class HomePageState {
    data object Loading : HomePageState()
    data object Success : HomePageState()
}