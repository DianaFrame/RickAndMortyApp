package com.example.domain.usecases

import com.example.domain.CharactersRepository

class InsertCharactersIntoDbUseCase(private val repository: CharactersRepository) {
    suspend fun execute(page: Int) {
        repository.insertListIntoDb(page)
    }
}