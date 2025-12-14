package ru.application.news_app.presentation.screen.main.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.application.news_app.domain.dao.AuthState
import ru.application.news_app.domain.dao.AuthViewModel
import ru.application.news_app.presentation.navigation.Screen


@Composable
fun MainScreenTabAll(
    onNavigationTo: (Screen) -> Unit = {},
    authViewModel: AuthViewModel = viewModel()
) {

    MainScreenTabAllView(
        onNavigationTo = onNavigationTo,
        authViewModel = authViewModel
    )

}
@Composable
fun MainScreenTabAllView(
    onNavigationTo: (Screen) -> Unit = {},
    authViewModel: AuthViewModel = AuthViewModel(),
){
    val authState = authViewModel.authState.observeAsState()
    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Unauthenticated -> onNavigationTo(Screen.Login)
            else -> Unit
        }
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "MainScreenTabAll"
        )
        Button(
            onClick = {
                authViewModel.signOut()
            }
        ) {
            Text(
                text = "singOut"
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    MainScreenTabAllView()
}