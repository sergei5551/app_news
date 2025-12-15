package ru.application.news_app.presentation.navigation

import RecoverPasswordScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import ru.application.news_app.domain.dao.AuthViewModel
import ru.application.news_app.presentation.screen.login.LoginScreen
import ru.application.news_app.presentation.screen.main.MainScreen
import ru.application.news_app.presentation.screen.register.RegisterScreen


sealed class Screen(){
    @Serializable
    data object Login : Screen()
    @Serializable
    data object Register : Screen()
    @Serializable
    data object MainScreen : Screen()

    @Serializable
    data object RecoverPassword: Screen()
}

@Composable
fun MainNav(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel,
    isLoggedIn: Boolean
){

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = if(isLoggedIn) Screen.MainScreen else Screen.Login
    ){
        composable<Screen.Login>{
            LoginScreen(onNavigationTo = { navigateTo -> navHostController.navigate(navigateTo) }, authViewModel = authViewModel)
        }

        composable<Screen.Register>{
            RegisterScreen(onNavigationTo = { navigateTo -> navHostController.navigate(navigateTo) }, authViewModel = authViewModel)
        }

        composable<Screen.RecoverPassword>{
            RecoverPasswordScreen (onNavigationTo = { navigateTo -> navHostController.navigate(navigateTo) } )
        }

        composable<Screen.MainScreen>{
            MainScreen(onNavigationTo = { navigateTo -> navHostController.navigate(navigateTo) }, authViewModel = authViewModel)
        }


    }
}