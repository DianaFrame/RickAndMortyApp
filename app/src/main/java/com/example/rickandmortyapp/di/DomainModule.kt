package com.example.rickandmortyapp.di

import com.example.domain.CharactersRepository
import com.example.domain.usecases.DeleteFavouriteUseCase
import com.example.domain.usecases.GetCharacterDetailsUseCase
import com.example.domain.usecases.GetCharacterListUseCase
import com.example.domain.usecases.GetFavouriteUseCase
import com.example.domain.usecases.GetSearchCharacterListUseCase
import com.example.domain.usecases.InsertFavouriteUseCase
import com.example.rickandmortyapp.event.Event
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

    @Provides
    fun provideGetFavouriteUseCase(repository: CharactersRepository): GetFavouriteUseCase{
        return GetFavouriteUseCase(repository = repository)
    }

    @Provides
    fun providesInsertFavouriteUseCase(repository: CharactersRepository): InsertFavouriteUseCase {
        return InsertFavouriteUseCase(repository = repository)
    }

    @Provides
    fun providesDeleteFavouriteUseCase(repository: CharactersRepository) : DeleteFavouriteUseCase{
        return DeleteFavouriteUseCase(repository = repository)
    }
}