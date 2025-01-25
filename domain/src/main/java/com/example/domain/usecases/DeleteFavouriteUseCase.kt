package com.example.domain.usecases

import com.example.domain.CharactersRepository
import com.example.domain.models.CharacterListItem

class DeleteFavouriteUseCase(private val repository: CharactersRepository) {
    suspend fun execute(characterListItem: CharacterListItem) {
        repository.deleteFavorite(characterListItem)
    }
}