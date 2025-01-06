package com.example.rickandmortyapp.event

sealed interface CharacterListEvent {
    data class UpdateQuery(val newQuery: String) : CharacterListEvent
}