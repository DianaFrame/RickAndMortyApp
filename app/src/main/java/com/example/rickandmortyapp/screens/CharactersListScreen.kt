package com.example.rickandmortyapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.domain.models.CharacterListItem
import com.example.rickandmortyapp.event.Event
import com.example.rickandmortyapp.navigation.Screen
import com.example.rickandmortyapp.state.State
import com.example.rickandmortyapp.viewmodel.MainViewModel

@Composable
fun CharacterListScreen(
    onNavigateTo: (Screen) -> Unit,
    viewModel: MainViewModel
) {
    CharactersListView(
        state = viewModel.state,
        onNavigateTo = onNavigateTo,
        onEvent = viewModel::onEvent
    )
}


@Preview(showSystemUi = true)
@Composable
fun CharactersListView(
    onNavigateTo: (Screen) -> Unit = {},
    state: State = State(),
    onEvent: (Event) -> Unit = {},
) {
    val characters = state.characterList?.results
    Column {
        OutlinedTextField(
            value = state.query,
            onValueChange = {
                onEvent(Event.UpdateQuery(it))
            },
            leadingIcon = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Outlined.Search),
                    contentDescription = "search"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            placeholder = {
                Text(text = "Введите запрос")
            }
        )
        LazyVerticalGrid(GridCells.Fixed(3), modifier = Modifier.fillMaxSize()) {
            items(characters ?: emptyList()) { character ->
                ListItem(character)
            }
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

