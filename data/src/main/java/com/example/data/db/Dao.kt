package com.example.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.models.Character


@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Character)

    @Delete
    suspend fun deleteCharacter(character: Character)

    @Query("SELECT * FROM characters WHERE isFav = 1")
    suspend fun getFavouriteCharacters(): List<Character>
}