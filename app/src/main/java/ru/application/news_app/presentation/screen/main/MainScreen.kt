package ru.application.news_app.presentation.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.application.news_app.domain.dao.AuthViewModel
import ru.application.news_app.presentation.navigation.Screen
import ru.application.news_app.presentation.screen.main.feed.MainScreenTabAll
import ru.application.news_app.presentation.screen.main.navigation.MainScreenNavigationRoute
import ru.application.news_app.presentation.screen.main.profile.ProfileScreen
import ru.application.news_app.presentation.screen.main.settings.SettingsScreen
import ru.application.news_app.presentation.ui.component.CustomGradientNavigationBar


@Composable
fun MainScreen(
    onNavigationTo: (Screen) -> Unit = {},
    authViewModel: AuthViewModel = AuthViewModel()
) {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            CustomGradientNavigationBar(navController)
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = MainScreenNavigationRoute.Feed
        ) {
            composable<MainScreenNavigationRoute.Settings> {
                SettingsScreen()
            }
            composable<MainScreenNavigationRoute.Feed> {
                MainScreenTabAll()
            }
            composable<MainScreenNavigationRoute.Profile> {
                ProfileScreen(onNavigationTo, authViewModel)
            }
        }
    }
}

