package com.example.domain.usecases

import com.example.domain.CharactersRepository
import com.example.domain.models.CharacterDetails

class GetCharacterDetailsUseCase(private val repository: CharactersRepository) {
    suspend fun execute(id: Int): CharacterDetails? {
        return repository.getDetailsById(id = id)
    }
}