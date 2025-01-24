package com.example.rickandmortyapp.state

import com.example.domain.models.CharacterDetails

data class State(
    val query: String = "",
    val characterDetails: CharacterDetails? = null,
    val isLoading: Boolean = true,
    val error: String? = null,
)
