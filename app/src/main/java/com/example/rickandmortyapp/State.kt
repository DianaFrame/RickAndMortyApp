package com.example.rickandmortyapp

sealed interface State {
    data object Error : State
    class Load: State
    class Success: State
}