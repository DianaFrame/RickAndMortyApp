package com.example.rickandmortyapp.event

sealed interface Event {
    data class UpdateQuery(val newQuery: String) : Event
    data class SearchCharacterByName(val name: String) : Event
    data object GetCharacterList : Event
    data class GetDetailsById(val id: String) : Event
}