package com.example.rickandmortyapp

sealed interface UIState {

    data class Error(val message: String) : UIState

    data class Loading(val isLoading: Boolean) : UIState

    data class Success(val data: Any) : UIState

}