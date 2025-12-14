package ru.application.news_app.presentation.screen.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.application.news_app.R
import ru.application.news_app.domain.dao.AuthState
import ru.application.news_app.domain.dao.AuthViewModel
import ru.application.news_app.presentation.navigation.Screen
import ru.application.news_app.presentation.ui.component.CustomTextField
import ru.application.news_app.presentation.ui.component.StyledButton

@Composable
fun LoginScreen(
    onNavigationTo: (Screen) -> Unit,
    authViewModel: AuthViewModel
) {
    val viewModel = viewModel<LoginScreenViewModel>()
    LoginView(
        state = viewModel.state,
        onNavigationTo = onNavigationTo,
        onEvent = viewModel::onEvent,
        authViewModel = authViewModel
    )
}

@Composable
fun LoginView(
    onNavigationTo: (Screen) -> Unit = {},
    state: LoginScreenState = LoginScreenState(),
    onEvent: (LoginScreenEvent) -> Unit = {},
    authViewModel: AuthViewModel = AuthViewModel(),
) {
    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current
    LaunchedEffect(authState.value) {
        when(val currentState = authState.value) {
            is AuthState.Authenticated -> onNavigationTo(Screen.MainTabAll)
            is AuthState.Error -> {
                Toast.makeText(
                    context,
                    currentState.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> Unit
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFAAE16),
                        Color(0xFFE1CF29),
                        Color(0xFFF0E268),
                        Color(0xFFFEF6A8)
                    ),
                    startY = 0f,
                    endY = 2200f
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Название
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(330.dp)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.n),
                    contentDescription = "char_N",
                    modifier = Modifier
                        .size(79.dp, 270.dp)
                        .padding(top = 50.dp)

                )
                Image(
                    painter = painterResource(id = R.drawable.e),
                    contentDescription = "char_E",
                    modifier = Modifier
                        .size(62.dp, 273.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.w),
                    contentDescription = "char_W",
                    modifier = Modifier
                        .size(96.dp, 273.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.s),
                    contentDescription = "char_S",
                    modifier = Modifier
                        .size(67.dp, 273.dp)
                        .padding(top = 50.dp)
                )
            }

            Image(
                painter = painterResource(id = R.drawable.login_image),
                contentDescription = "login_image",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 200.dp)
                    .size(100.dp)
            )
        }

        // Основной контент с weight
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .align(Alignment.TopCenter)
            ) {
                CustomTextField(
                    value = state.email,
                    onValueChange = {
                        onEvent(LoginScreenEvent.EmailUpdated(it))
                    },
                    leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email") },
                    placeholder = {
                        Text(text = stringResource(id = R.string.enter_email))
                    },
                    modifier = Modifier
                )
                CustomTextField(
                    value = state.password,
                    onValueChange = {
                        onEvent(LoginScreenEvent.PasswordUpdated(it))
                    },
                    leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password") },
                    visualTransformation = if (state.isPasswordVisible) {
                        VisualTransformation.None // Показывать текст
                    } else {
                        PasswordVisualTransformation() // Скрывать текст
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                onEvent(LoginScreenEvent.IsPasswordVisibleUpdated(!state.isPasswordVisible))
                            }
                        ) {
                            Icon(
                                imageVector = if (state.isPasswordVisible) {
                                    Icons.Filled.Visibility
                                } else {
                                    Icons.Filled.VisibilityOff
                                },
                                contentDescription = if (state.isPasswordVisible) {
                                    "Скрыть пароль"
                                } else {
                                    "Показать пароль"
                                }
                            )
                        }
                    },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.enter_password),
                        )
                    },
                    modifier = Modifier.padding(top = 12.dp),

                    )
            }
            Image(
                painter = painterResource(id = R.drawable.back_image_newspaper),
                contentDescription = "newspaper",
                modifier = Modifier
                    .size(400.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                StyledButton(
                    onClick = {
                        authViewModel.login(state.email, state.password)
                    },
                    modifier = Modifier.padding(top = 150.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.login)
                    )
                }

                StyledButton(
                    onClick = {
                        onNavigationTo(Screen.Register)
                    },
                ) {
                    Text(
                        text = stringResource(id = R.string.register)
                    )
                }
                TextButton(
                    onClick = {onNavigationTo(Screen.RecoverPassword)},
                    modifier = Modifier.padding(top = 15.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.forgot_password),
                        color = Color.Blue
                    )
                }
            }
        }

        // Социальные иконки
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp), // Отступ снизу
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.continue_with),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.vk),
                    contentDescription = "vk",
                    modifier = Modifier
                        .size(50.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.gmail),
                    contentDescription = "gmail",
                    modifier = Modifier
                        .size(50.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.classmates),
                    contentDescription = "classmates",
                    modifier = Modifier
                        .size(50.dp)

                )
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    LoginView()
}