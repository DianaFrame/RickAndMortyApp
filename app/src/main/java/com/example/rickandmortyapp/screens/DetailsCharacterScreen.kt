package com.example.rickandmortyapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.rickandmortyapp.state.State
import com.example.rickandmortyapp.viewmodel.MainViewModel

@Composable
fun DetailsCharacterScreen(
    viewModel: MainViewModel
) {
    DetailsCharacterView(
        state = viewModel.state,
    )
}

@Composable
fun DetailsCharacterView(
    state: State = State(),
) {
    val characterDetails = state.characterDetails
    if (characterDetails != null) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = characterDetails.image,
                    contentDescription = "image",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize()

                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = characterDetails.name)
                        Text(text = characterDetails.type)
                        Text(text = characterDetails.gender)
                        Text(text = characterDetails.status)
                    }
                }
            }
        }
    }

}