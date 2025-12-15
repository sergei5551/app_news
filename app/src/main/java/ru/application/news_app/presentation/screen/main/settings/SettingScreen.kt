package ru.application.news_app.presentation.screen.main.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ru.application.news_app.presentation.navigation.Screen

@Composable
fun SettingsScreen() {
    SettingsScreenView()
}
@Composable
fun SettingsScreenView(){

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .background(color = Color(0xFFF5EAEA))
    ) {
        Text(
            text = "SettingsScreen",
            color = Color.Black
        )

    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    SettingsScreen()
}