package com.example.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.models.Character
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Character)

    @Delete
    suspend fun deleteCharacter(character: Character)

    @Query("SELECT * FROM characters WHERE isFav = 1")
    fun getFavouriteCharacters(): Flow<List<Character>>

    @Query("SELECT * FROM characters")
    fun getAllCharacters(): Flow<List<Character>>

    @Query("SELECT * FROM characters WHERE name LIKE '%' || :query || '%'")
    fun searchCharactersByName(query: String): Flow<List<Character>>
}