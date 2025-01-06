package com.example.rickandmortyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortyapp.navigation.MainNavigation
import com.example.rickandmortyapp.ui.theme.RickAndMortyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyAppTheme {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier
) {
    MainNavigation(
        navHostController = rememberNavController(),
        modifier = modifier
    )
}