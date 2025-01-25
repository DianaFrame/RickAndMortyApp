package com.example.domain.usecases

import com.example.domain.CharactersRepository
import com.example.domain.models.CharacterList
import com.example.domain.models.CharacterListItem
import kotlinx.coroutines.flow.Flow

class GetSearchCharacterListUseCase(private val repository: CharactersRepository) {
    suspend fun execute(name: String): Flow<List<CharacterListItem>> {
        return repository.getSearchListByName(name = name)
    }
}