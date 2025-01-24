package com.example.rickandmortyapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.CharacterListItem
import com.example.domain.usecases.GetCharacterDetailsUseCase
import com.example.domain.usecases.GetCharacterListUseCase
import com.example.domain.usecases.GetSearchCharacterListUseCase
import com.example.rickandmortyapp.event.Event
import com.example.rickandmortyapp.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
    private var currentPage = 1
    private val _characters = MutableStateFlow<List<CharacterListItem>>(emptyList())
    val characters: StateFlow<List<CharacterListItem>> get() = _characters


    init {
        loadNextPage()
    }

    fun onEvent(event: Event) {
        when (event) {
            is Event.UpdateQuery -> {
                this.state = state.copy(query = event.newQuery)
            }

            is Event.SearchCharacterByName -> {
                this.state = state.copy(isLoading = true)
                if (event.name != "") {
                    getSearchCharacters()
                }

            }

            is Event.GetDetailsById -> {
                this.state = state.copy(isLoading = true)
                getDetailsById(event.id)

            }


        }


    }

    private fun getSearchCharacters() = viewModelScope.launch(Dispatchers.IO) {
        val characters = getSearchCharacterListUseCase.execute(name = state.query)
        this@MainViewModel.state =
            state.copy(isLoading = false)
        this@MainViewModel._characters.value = characters?.results.orEmpty()
    }

    private fun getDetailsById(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        val details = getCharacterDetailsUseCase.execute(id)
        this@MainViewModel.state =
            state.copy(characterDetails = details, isLoading = false)
    }

    fun loadNextPage() {
        viewModelScope.launch {
            try {
                this@MainViewModel.state = state.copy(isLoading = true)
                val newCharacters = getCharacterListUseCase.execute(currentPage)
                _characters.value += newCharacters?.results.orEmpty()
                currentPage++
            } catch (e: Exception) {
                Log.e("MyLog", "error")
            } finally {
                this@MainViewModel.state = state.copy(isLoading = false)
            }

        }
    }

}