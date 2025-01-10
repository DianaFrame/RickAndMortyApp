package com.example.rickandmortyapp.di

import com.example.data.CharactersRepositoryImpl
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
    fun provideCharactersRepository(): CharactersRepository {
        return CharactersRepositoryImpl()
    }
}