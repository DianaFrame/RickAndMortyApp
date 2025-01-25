package com.example.domain.usecases

import com.example.domain.CharactersRepository
import com.example.domain.models.CharacterListItem

class InsertFavouriteUseCase(private val repository: CharactersRepository) {
    suspend fun execute(characterListItem: CharacterListItem) {
        repository.insertFavorite(characterListItem)
    }
}