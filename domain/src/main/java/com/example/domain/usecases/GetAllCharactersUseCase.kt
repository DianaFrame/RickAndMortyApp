package com.example.domain.usecases

import com.example.domain.CharactersRepository
import com.example.domain.models.CharacterListItem
import kotlinx.coroutines.flow.Flow

class GetAllCharactersUseCase(private val repository: CharactersRepository) {
    suspend fun execute(): Flow<List<CharacterListItem>> {
        return repository.getList()
    }
}