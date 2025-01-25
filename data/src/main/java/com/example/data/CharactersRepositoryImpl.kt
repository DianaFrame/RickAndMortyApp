package com.example.data

import com.example.data.api.CharactersApi
import com.example.data.db.MainDb
import com.example.data.models.Character
import com.example.data.models.Characters
import com.example.data.models.Details
import com.example.domain.CharactersRepository
import com.example.domain.models.CharacterDetails
import com.example.domain.models.CharacterList
import com.example.domain.models.CharacterListItem
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    val mainDb: MainDb
) : CharactersRepository {
    private val api: CharactersApi by lazy {
        Retrofit.getClient().create(CharactersApi::class.java)
    }

    override suspend fun insertListIntoDb(page: Int) {
        val characters = api
            .getAllCharacters(page)
            .body()
            ?.toCharacterList()
        characters?.results?.forEach { character ->
            mainDb.dao.insertCharacter(character.toCharacter())
        }
    }

    override suspend fun getDetailsById(id: Int): CharacterDetails? {
        return api
            .getDetailsById(id = id)
            .body()
            ?.toCharacterDetails()
    }

    override suspend fun getSearchListByName(name: String): CharacterList {
        return mainDb.dao.searchCharactersByName(query = name).toCharacterList()
    }

    override suspend fun getList(): CharacterList {
        return mainDb.dao.getAllCharacters().toCharacterList()
    }

    override suspend fun getFavourites(): CharacterList {
        return mainDb.dao.getFavouriteCharacters().toCharacterList()
    }

    override suspend fun insertFavorite(characterListItem: CharacterListItem) {
        mainDb.dao.insertCharacter(characterListItem.toCharacter())
    }

    override suspend fun deleteFavorite(characterListItem: CharacterListItem) {
        mainDb.dao.deleteCharacter(characterListItem.toCharacter())
    }

    private fun Characters.toCharacterList(): CharacterList {
        return CharacterList(results = this.results.map { it.toCharacterListItem() })
    }

    private fun Character.toCharacterListItem(): CharacterListItem {
        return CharacterListItem(
            id = this.id,
            name = this.name,
            image = this.image,
            isFav = this.isFav
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

    private fun CharacterListItem.toCharacter(): Character {
        return Character(
            id = this.id,
            name = this.name,
            image = this.image,
            isFav = this.isFav
        )
    }

    private fun List<Character>.toCharacterList(): CharacterList {
        return CharacterList(
            results = this.map { it.toCharacterListItem() }
        )
    }

}

