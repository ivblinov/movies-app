package com.examples.moviesapp.presentation.states

sealed class AllButtonState {
    data object Visible: AllButtonState()
    data object Gone: AllButtonState()
}