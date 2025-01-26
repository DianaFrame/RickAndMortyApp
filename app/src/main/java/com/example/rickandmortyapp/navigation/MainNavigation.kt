package com.example.rickandmortyapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rickandmortyapp.screens.CharacterListScreen
import com.example.rickandmortyapp.screens.DetailsCharacterScreen
import com.example.rickandmortyapp.screens.LoadingScreen
import com.example.rickandmortyapp.viewmodel.MainViewModel
import kotlinx.serialization.Serializable
import androidx.compose.animation.*
import androidx.navigation.compose.*



sealed interface Screen {

    @Serializable
    data object Loading : Screen

    @Serializable
    data object DetailsCharacter : Screen

    @Serializable
    data object CharacterList : Screen
}


@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: MainViewModel,
) {

    NavHost(
        modifier = Modifier,
        navController = navHostController,
        startDestination = Screen.Loading
    ) {
        composable<Screen.Loading> {
            LoadingScreen(onNavigateTo = { navigateTo ->
                navHostController.navigate(navigateTo){}
            }, viewModel = viewModel)
        }
        composable<Screen.CharacterList> {
            CharacterListScreen(onNavigateTo = { navigateTo ->
                navHostController.navigate(navigateTo)
            }, viewModel = viewModel)
        }
        composable<Screen.DetailsCharacter> {
            DetailsCharacterScreen(viewModel = viewModel)
        }
    }
}