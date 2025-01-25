package com.example.domain

import com.example.domain.models.CharacterDetails
import com.example.domain.models.CharacterList
import com.example.domain.models.CharacterListItem
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    suspend fun insertListIntoDb(page: Int)

    suspend fun getDetailsById(id: Int): CharacterDetails?

    suspend fun getSearchListByName(name: String): Flow<List<CharacterListItem>>

    suspend fun getList(): Flow<List<CharacterListItem>>

    suspend fun getFavourites(): Flow<List<CharacterListItem>>

    suspend fun insertFavorite(characterListItem: CharacterListItem)

    suspend fun deleteFavorite(characterListItem: CharacterListItem)

}