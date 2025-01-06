package com.example.rickandmortyapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.rickandmortyapp.event.CharacterListEvent
import com.example.rickandmortyapp.state.CharacterListScreenState

class CharacterListViewModel : ViewModel() {
    var state by mutableStateOf(CharacterListScreenState())
        private set

    fun onEvent(characterListEvent: CharacterListEvent){
        when(characterListEvent){
            is CharacterListEvent.UpdateQuery -> {state = state.copy(query = characterListEvent.newQuery)}
        }
    }

}