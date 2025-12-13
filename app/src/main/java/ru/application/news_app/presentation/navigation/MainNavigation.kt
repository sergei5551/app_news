package ru.application.news_app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

import ru.application.news_app.presentation.screen.login.LoginScreen
import ru.application.news_app.presentation.screen.main.home.MainScreenTabAll
import ru.application.news_app.presentation.screen.main.home.MainScreenTabFavorites
import ru.application.news_app.presentation.screen.main.home.MainScreenTabSection
import ru.application.news_app.presentation.screen.register.RegisterScreen

sealed class Screen(){
    @Serializable
    data object Login : Screen()
    @Serializable
    data object Register : Screen()
    @Serializable
    data object MainTabAll : Screen()

    @Serializable
    data object MainTabSection : Screen()

    @Serializable
    data object MainTabFavorites : Screen()
}

@Composable
fun MainNav(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
){
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = Screen.Login
    ){
        composable<Screen.Login>{
            LoginScreen(
                onNavigationTo = { navigateTo -> navHostController.navigate(navigateTo) }
            )
        }

        composable<Screen.Register>{
            RegisterScreen(
                onNavigationTo = { navigateTo -> navHostController.navigate(navigateTo) }
            )
        }

        composable<Screen.MainTabAll>{
            MainScreenTabAll { navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }

        composable<Screen.MainTabSection>{
            MainScreenTabSection { navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }

        composable<Screen.MainTabFavorites>{
            MainScreenTabFavorites { navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }
    }
}