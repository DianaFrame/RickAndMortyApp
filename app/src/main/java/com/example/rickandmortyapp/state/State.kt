package com.example.rickandmortyapp.state

import com.example.domain.models.CharacterDetails
import com.example.domain.models.CharacterList

data class State(
    val query: String = "",
    val characterList: CharacterList? = null,
    val selectCharacterId: Int? = null,
    val characterDetails: CharacterDetails? = null,
    val isLoading: Boolean = true,
    val error: String? = null,
)
