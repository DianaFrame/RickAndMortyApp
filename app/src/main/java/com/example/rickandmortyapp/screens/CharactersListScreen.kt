package com.example.rickandmortyapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil3.compose.AsyncImage
import com.example.domain.models.CharacterListItem
import com.example.rickandmortyapp.event.Event
import com.example.rickandmortyapp.navigation.Screen
import com.example.rickandmortyapp.state.State
import com.example.rickandmortyapp.ui.theme.Gray80
import com.example.rickandmortyapp.ui.theme.Red80
import com.example.rickandmortyapp.ui.theme.White60
import com.example.rickandmortyapp.viewmodel.MainViewModel

@Composable
fun CharacterListScreen(
    onNavigateTo: (Screen) -> Unit,
    viewModel: MainViewModel
) {
    CharactersListView(
        state = viewModel.state,
        onNavigateTo = onNavigateTo,
        onEvent = viewModel::onEvent,
        viewModel = viewModel
    )
}


@Composable
fun CharactersListView(
    onNavigateTo: (Screen) -> Unit = {},
    state: State = State(),
    onEvent: (Event) -> Unit = {},
    viewModel: MainViewModel
) {
    val characters by viewModel.characters.collectAsState()
    Column {
        OutlinedTextField(
            value = state.query,
            onValueChange = { query ->
                onEvent(Event.UpdateQuery(query))
                onEvent(Event.SearchCharacterByName(query))
            },
            leadingIcon = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Outlined.Search),
                    contentDescription = "search"
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        onEvent(Event.GetFavouriteCharacters)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite",
                        tint = if (state.isFav) Red80 else Gray80
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            placeholder = {
                Text(text = "Введите запрос")
            }
        )
        LazyVerticalGrid(
            GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
        ) {
            itemsIndexed(characters) { index, character ->
                if (index >= characters.size - 5
                    && !state.isLoading
                    && !state.isFav
                    && state.query.trim() == ""
                ) {
                    viewModel.loadNextPage()

                }
                ListItem(character, onEvent) {
                    onNavigateTo(Screen.DetailsCharacter)
                }

            }
        }

    }


}

@Composable
fun ListItem(
    characterListItem: CharacterListItem,
    onEvent: (Event) -> Unit,
    onClick: () -> Unit,
) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onEvent(Event.GetDetailsById(characterListItem.id))
                onClick()
            }

    ) {
        ConstraintLayout(
            Modifier
                .fillMaxWidth(),
        ) {
            val (image, name, favouriteButton) = createRefs()
            AsyncImage(
                model = characterListItem.image,
                contentDescription = "character image",
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.Crop,
            )
            Text(
                text = characterListItem.name,
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(name) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            IconButton(
                onClick = {
                    onEvent(
                        Event.InsertFavourite(
                            characterListItem.copy(isFav = !characterListItem.isFav)
                        )
                    )
                },
                modifier = Modifier.constrainAs(favouriteButton) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "favourite",
                    tint = if (characterListItem.isFav) Red80 else Gray80,
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(White60)
                        .padding(3.dp)
                )
            }
        }

    }
}

