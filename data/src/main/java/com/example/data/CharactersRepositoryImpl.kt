package com.example.data

import com.example.data.api.CharactersApi
import com.example.data.models.Character
import com.example.data.models.Characters
import com.example.data.models.Details
import com.example.domain.CharactersRepository
import com.example.domain.models.CharacterDetails
import com.example.domain.models.CharacterList
import com.example.domain.models.CharacterListItem

class CharactersRepositoryImpl : CharactersRepository {
    private val api: CharactersApi by lazy {
        Retrofit.getClient().create(CharactersApi::class.java)
    }

    override suspend fun getList(page: Int): CharacterList? {
        return api
            .getAllCharacters(page)
            .body()
            ?.toCharacterList()
    }

    override suspend fun getDetailsById(id: Int): CharacterDetails? {
        return api
            .getDetailsById(id = id)
            .body()
            ?.toCharacterDetails()
    }

    override suspend fun getSearchListByName(name: String): CharacterList? {
        return api
            .searchByName(name = name)
            .body()
            ?.toCharacterList()
    }

    private fun Characters.toCharacterList(): CharacterList {
        return CharacterList(results = this.results.map { it.toCharacterListItem() })
    }

    private fun Character.toCharacterListItem(): CharacterListItem {
        return CharacterListItem(
            id = this.id,
            name = this.name,
            image = this.image
        )
    }

    private fun Details.toCharacterDetails(): CharacterDetails {
        return CharacterDetails(
            id = this.id,
            name = this.name,
            type = this.type,
            image = this.image,
            status = this.status,
            gender = this.gender
        )
    }

}

