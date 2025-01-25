package com.example.domain.usecases

import com.example.domain.CharactersRepository
import com.example.domain.models.CharacterList

class GetAllCharactersUseCase(private val repository: CharactersRepository) {
    suspend fun execute(): CharacterList? {
        return repository.getList()
    }
}