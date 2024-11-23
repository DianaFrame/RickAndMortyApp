package com.example.domain

import com.example.domain.models.CharacterDetails
import com.example.domain.models.CharacterList

interface CharactersRepository {

    suspend fun getList(): CharacterList?

    suspend fun getDetailsById(id: Int): CharacterDetails?

    suspend fun getSearchListByName(name: String): CharacterList?

}