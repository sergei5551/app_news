package ru.application.news_app.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import ru.application.news_app.presentation.navigation.Screen

@Composable
fun MainScreenTabFavorites(
    onNavigationTo: (Screen) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "MainScreenTabFavorites"
        )
    }
}