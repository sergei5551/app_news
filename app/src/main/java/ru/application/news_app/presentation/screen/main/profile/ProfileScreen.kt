package ru.application.news_app.presentation.screen.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.application.news_app.domain.dao.AuthState
import ru.application.news_app.domain.dao.AuthViewModel
import ru.application.news_app.presentation.navigation.Screen

@Composable
fun ProfileScreen(
    onNavigationTo: (Screen) -> Unit = {},
    authViewModel: AuthViewModel = viewModel()
) {
    val authState = authViewModel.authState.observeAsState()
    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Unauthenticated -> onNavigationTo(Screen.Login)
            else -> Unit
        }
    }
    ProfileScreenView(
        onNavigationTo = onNavigationTo,
        authViewModel = authViewModel
    )

}
@Composable
fun ProfileScreenView(
    onNavigationTo: (Screen) -> Unit = {},
    authViewModel: AuthViewModel? = null,
    authState: AuthState = AuthState.Unauthenticated
){

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .background(color = Color(0xFFF5EAEA))
    ) {
        Text(
            text = "Profile",
            color = Color.Black
        )
        Button(
            onClick = {
                authViewModel?.signOut()
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
    ProfileScreenView()
}