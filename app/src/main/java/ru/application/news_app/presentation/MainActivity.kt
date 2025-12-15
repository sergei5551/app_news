package ru.application.news_app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.application.news_app.domain.dao.AuthState
import ru.application.news_app.domain.dao.AuthViewModel
import ru.application.news_app.presentation.navigation.MainNav
import ru.application.news_app.presentation.ui.theme.NewsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authViewModel: AuthViewModel by viewModels()

        setContent {
            NewsAppTheme {
                    MainContent(
                        authViewModel = authViewModel,
                    )
                }
            }
        }
    }


@Composable
fun MainContent(
    authViewModel: AuthViewModel
) {
    // Наблюдаем за состоянием авторизации
    val authState by authViewModel.authState.observeAsState()

    // Определяем вошли ли мы в систему
    val isLoggedIn = authState is AuthState.Authenticated

    // Показываем индикатор загрузки пока проверяем состояние
    when (authState) {
        AuthState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        else -> {
            // Когда состояние определено, показываем навигацию
            MainNav(
                authViewModel = authViewModel,
                isLoggedIn = isLoggedIn
            )
        }
    }
}
