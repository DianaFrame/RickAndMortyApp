package com.example.domain.usecases

import com.example.domain.CharactersRepository
import com.example.domain.models.CharacterList

class GetSearchCharacterListUseCase(private val repository: CharactersRepository) {
    suspend fun execute(name: String): CharacterList? {
        return repository.getSearchListByName(name = name)
    }
}