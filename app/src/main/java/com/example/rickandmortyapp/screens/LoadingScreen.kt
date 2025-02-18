package com.example.rickandmortyapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.rickandmortyapp.navigation.Screen
import com.example.rickandmortyapp.state.State
import com.example.rickandmortyapp.viewmodel.MainViewModel
import kotlinx.coroutines.delay


@Composable
fun LoadingScreen(
    onNavigateTo: (Screen) -> Unit,
    viewModel: MainViewModel,
) {
    LoadingView(
        state = viewModel.state,
        onNavigateTo = onNavigateTo,
    )
}

@Composable
fun LoadingView(
    onNavigateTo: (Screen) -> Unit = {},
    state: State = State(),
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("load_morty_flex.json")
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
    }
    LaunchedEffect(state.isLoading) {
        if (!state.isLoading) {
            delay(3000)
            onNavigateTo(Screen.CharacterList)
        }
    }
}