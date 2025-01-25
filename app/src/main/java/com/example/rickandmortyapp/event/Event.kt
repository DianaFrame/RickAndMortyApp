package com.example.rickandmortyapp.event

import com.example.data.models.Character
import com.example.domain.models.CharacterListItem

sealed interface Event {
    data class UpdateQuery(val newQuery: String) : Event
    data class SearchCharacterByName(val name: String) : Event
    data class GetDetailsById(val id: Int) : Event
    data class InsertFavourite(val characterListItem: CharacterListItem) : Event
    data class DeleteFavourite(val characterListItem: CharacterListItem) : Event
}