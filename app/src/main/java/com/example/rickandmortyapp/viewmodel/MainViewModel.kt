package com.example.rickandmortyapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetCharacterDetailsUseCase
import com.example.domain.usecases.GetCharacterListUseCase
import com.example.domain.usecases.GetSearchCharacterListUseCase
import com.example.rickandmortyapp.event.Event
import com.example.rickandmortyapp.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase,
    private val getSearchCharacterListUseCase: GetSearchCharacterListUseCase,
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
) : ViewModel() {
    var state by mutableStateOf(State())
        private set

    fun onEvent(event: Event) {
        when (event) {
            is Event.UpdateQuery -> {
                this.state = state.copy(query = event.newQuery)
            }

            is Event.GetCharacterList -> {
                getCharacters()
            }

            is Event.SearchCharacterByName -> {
                if (event.name != "") {
                    viewModelScope.launch(Dispatchers.IO) {
                        val characters = getSearchCharacterListUseCase.execute(name = state.query)
                        this@MainViewModel.state =
                            state.copy(characterList = characters, isLoading = false)
                    }
                }

            }

            is Event.GetDetailsById -> {
                if (state.selectCharacterId != null) {
                    viewModelScope.launch(Dispatchers.IO) {
                        val details = getCharacterDetailsUseCase.execute(state.selectCharacterId!!)
                        this@MainViewModel.state =
                            state.copy(characterDetails = details, isLoading = false)
                    }
                }
            }


        }


    }

    private fun getCharacters() = viewModelScope.launch(Dispatchers.IO) {
        val characters = getCharacterListUseCase.execute()
        this@MainViewModel.state = state.copy(characterList = characters, isLoading = false)
    }
}