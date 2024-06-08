package com.example.cstichallenge.features.common

sealed class UiState {
    data class SnackbarEvent(val message : String) : UiState()
    data class NavigateEvent(val route: String) : UiState()
}