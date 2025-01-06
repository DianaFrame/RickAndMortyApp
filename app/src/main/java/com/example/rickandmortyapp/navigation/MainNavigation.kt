package com.example.rickandmortyapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rickandmortyapp.screens.CharacterListScreen
import com.example.rickandmortyapp.screens.DetailsCharacterScreen
import com.example.rickandmortyapp.screens.LoadingScreen
import kotlinx.serialization.Serializable

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
    navHostController: NavHostController
) {
    NavHost(
        modifier = Modifier,
        navController = navHostController,
        startDestination = Screen.Loading
    ) {
        composable<Screen.Loading> {
            LoadingScreen { navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }
        composable<Screen.CharacterList> {
            CharacterListScreen (onNavigateTo = { navigateTo ->
                navHostController.navigate(navigateTo)
            })
        }
        composable<Screen.DetailsCharacter> {
            DetailsCharacterScreen { navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }
    }
}