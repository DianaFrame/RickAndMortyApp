package com.example.rickandmortyapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.domain.models.CharacterListItem
import com.example.rickandmortyapp.navigation.Screen


@Composable
fun CharactersListScreen(
    onNavigateTo: (Screen) -> Unit
) {
    var queryText by remember { mutableStateOf("") }
    val characters = listOf(
        CharacterListItem(
            1,
            "Morty",
            "https://rickandmortyapi.com/api/character/avatar/2.jpeg"
        ),
        CharacterListItem(
            2,
            "Morty",
            "https://rickandmortyapi.com/api/character/avatar/2.jpeg"
        ),
        CharacterListItem(
            3,
            "Morty",
            "https://rickandmortyapi.com/api/character/avatar/2.jpeg"
        ),
        CharacterListItem(
            4,
            "Morty",
            "https://rickandmortyapi.com/api/character/avatar/2.jpeg"
        )
    )
    LazyVerticalGrid(GridCells.Fixed(3), modifier = Modifier.fillMaxSize()) {
        items(characters) { character ->
            ListItem(character)
        }
    }
}

@Composable
fun ListItem(characterListItem: CharacterListItem) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)

    ) {
        Column(
            Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                model = characterListItem.image,
                contentDescription = "character image"
            )
            Text(
                text = characterListItem.name,
            )
        }

    }
}

