package com.example.rickandmortyapp.di

import android.app.Application
import androidx.room.Room
import com.example.data.CharactersRepositoryImpl
import com.example.data.db.MainDb
import com.example.domain.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideCharactersRepository(db: MainDb): CharactersRepository {
        return CharactersRepositoryImpl(db)
    }

    @Provides
    @Singleton
    fun provideMainDb(app: Application): MainDb {
        return Room.databaseBuilder(
            app,
            MainDb::class.java,
            "characters.db"
        ).build()
    }
}
