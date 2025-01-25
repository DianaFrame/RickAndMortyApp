package com.example.domain

import com.example.domain.models.CharacterDetails
import com.example.domain.models.CharacterList
import com.example.domain.models.CharacterListItem

interface CharactersRepository {

    suspend fun insertListIntoDb(page: Int)

    suspend fun getDetailsById(id: Int): CharacterDetails?

    suspend fun getSearchListByName(name: String): CharacterList?

    suspend fun getList(): CharacterList?

    suspend fun getFavourites(): CharacterList?

    suspend fun insertFavorite(characterListItem: CharacterListItem)

    suspend fun deleteFavorite(characterListItem: CharacterListItem)

}