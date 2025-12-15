package ru.application.news_app.presentation.screen.register

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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockReset
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
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
fun RegisterScreen(
    onNavigationTo: (Screen) -> Unit = {},
    authViewModel: AuthViewModel
) {

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when(val currentState = authState.value) {
            is AuthState.Authenticated -> onNavigationTo(Screen.MainScreen)
            is AuthState.Error -> {
                Toast.makeText(
                    context,
                    currentState.message,
                    Toast.LENGTH_SHORT
                ).show()
                authViewModel.resetError()
            }
            else -> Unit
        }
    }
    val viewModel = viewModel<RegisterScreenViewModel>()
    RegisterView(
        state = viewModel.state,
        onNavigationTo = onNavigationTo,
        onEvent = viewModel::onEvent,
        authViewModel = authViewModel
    )
}

@Composable
fun RegisterView(
    onNavigationTo: (Screen) -> Unit = {},
    state: RegisterScreenState = RegisterScreenState(),
    onEvent: (RegisterScreenEvent) -> Unit = {},
    authViewModel: AuthViewModel? = null,
    authState: AuthState = AuthState.Unauthenticated
) {
    val context = LocalContext.current
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
                painter = painterResource(id = R.drawable.create_an_account),
                contentDescription = "login_image",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 200.dp)
                    .size(150.dp)
            )
        }
        // Контейнер элементов
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()

        ) {
            Image(
                painter = painterResource(id = R.drawable.pencil),
                contentDescription = "pencil",
                modifier = Modifier
                    .size(250.dp)
            )
            Column(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomTextField(
                    value = state.username,
                    onValueChange = {
                        onEvent(RegisterScreenEvent.UsernameUpdated(it))
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = "username"
                        )
                    },
                    placeholder = {
                        Text(text = stringResource(id = R.string.enter_username))
                    },
                    modifier = Modifier
                )
                CustomTextField(
                    value = state.email,
                    onValueChange = {
                        onEvent(RegisterScreenEvent.EmailUpdated(it))
                    },
                    leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email") },
                    placeholder = {
                        Text(text = stringResource(id = R.string.enter_email))
                    },

                    modifier = Modifier.padding(top = 12.dp),
                )
                CustomTextField(
                    value = state.password,
                    onValueChange = {
                        onEvent(RegisterScreenEvent.PasswordUpdated(it))
                    },
                    leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    placeholder = {
                        Text(text = stringResource(id = R.string.enter_password))
                    },
                    modifier = Modifier.padding(top = 12.dp),
                )
                CustomTextField(
                    value = state.repeatPassword,
                    onValueChange = {
                        onEvent(RegisterScreenEvent.RepeatPasswordUpdated(it))
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.LockReset,
                            contentDescription = "repeat_password"
                        )
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    placeholder = {
                        Text(text = stringResource(id = R.string.repeat_password))
                    },
                    modifier = Modifier.padding(top = 15.dp),
                )
                StyledButton(
                    onClick = {
                        // Проверяем пароли
                        if (state.password != state.repeatPassword) {
                            Toast.makeText(
                                context,
                                "Passwords do not match",
                                Toast.LENGTH_SHORT
                            ).show()

                            return@StyledButton
                        }
                        authViewModel?.signUp(
                            username = state.username,
                            email = state.email,
                            password = state.password
                        )
                    },
                    enabled = authState != AuthState.Loading,
                    modifier = Modifier.padding(top = 15.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.login)
                    )
                }

                TextButton(
                    onClick = { onNavigationTo(Screen.Login) },
                    enabled = authState != AuthState.Loading,
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.alr_registered),
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
fun RegisterScreenPreview() {
    RegisterView()
}