package com.examples.moviesapp.presentation.states

sealed class HomePageState {
    object Loading : HomePageState()
    object Success : HomePageState()
}