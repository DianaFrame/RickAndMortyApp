package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.models.Character

@Database(
    entities = [Character::class],
    version = 1
)
abstract class MainDb: RoomDatabase() {
        abstract val dao: Dao
}