package com.example.rickandmortyapp.di

import com.example.domain.CharactersRepository
import com.example.domain.usecases.GetCharacterDetailsUseCase
import com.example.domain.usecases.GetCharacterListUseCase
import com.example.domain.usecases.GetSearchCharacterListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {
    @Provides
    fun provideGetCharacterListUseCase(repository: CharactersRepository): GetCharacterListUseCase {
        return GetCharacterListUseCase(repository = repository)
    }

    @Provides
    fun provideGetSearchCharacterListUseCase(repository: CharactersRepository): GetSearchCharacterListUseCase {
        return GetSearchCharacterListUseCase(repository = repository)
    }

    @Provides
    fun provideGetCharacterDetailsUseCase(repository: CharactersRepository): GetCharacterDetailsUseCase {
        return GetCharacterDetailsUseCase(repository = repository)
    }
}