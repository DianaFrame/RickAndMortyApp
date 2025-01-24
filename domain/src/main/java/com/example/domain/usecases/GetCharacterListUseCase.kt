package com.example.domain.usecases

import com.example.domain.CharactersRepository
import com.example.domain.models.CharacterList

class GetCharacterListUseCase(private val repository: CharactersRepository) {
    suspend fun execute(page: Int): CharacterList? {
        return repository.getList(page)
    }
}