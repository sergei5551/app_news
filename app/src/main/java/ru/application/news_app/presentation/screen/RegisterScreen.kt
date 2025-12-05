package ru.application.news_app.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.application.news_app.R
import ru.application.news_app.presentation.navigation.Screen
import ru.application.news_app.presentation.screen.viewmodel.LoginScreenViewModel
import ru.application.news_app.presentation.screen.viewmodel.RegisterScreenViewModel
import ru.application.news_app.presentation.ui.component.CustomTextField
import ru.application.news_app.presentation.ui.component.StyledButton

@Composable
fun RegisterScreen(
    onNavigationTo: (Screen) -> Unit = {},
    viewModel: RegisterScreenViewModel = viewModel()
) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF9E6802),
            Color(0xFFBA7A04),
            Color(0xFFE89907)
        ),
        start = Offset(0f,200f),
        end = Offset(100f,0f),
    )
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
            Column(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomTextField(
                    value = viewModel.email,
                    onValueChange = viewModel::updateEmail,
                    leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email") },
                    placeholder = {
                        Text(text = stringResource(id = R.string.enter_email))
                    },
                    modifier = Modifier
                )
                CustomTextField(
                    value = viewModel.password,
                    onValueChange = viewModel::updatePassword,
                    leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    placeholder = {
                        Text(text = stringResource(id = R.string.enter_password))
                    },
                    modifier = Modifier.padding(top = 12.dp),
                )
                CustomTextField(
                    value = viewModel.email,
                    onValueChange = viewModel::updateEmail,
                    leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email") },
                    placeholder = {
                        Text(text = stringResource(id = R.string.enter_email))
                    },
                    modifier = Modifier.padding(top = 12.dp),
                )
                CustomTextField(
                    value = viewModel.email,
                    onValueChange = viewModel::updateEmail,
                    leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email") },
                    placeholder = {
                        Text(text = stringResource(id = R.string.enter_email))
                    },
                    modifier = Modifier.padding(top = 12.dp),
                )
                StyledButton(
                    onClick = {},
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.login)
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
        ){
            Text(
                text = stringResource(id = R.string.continue_with),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
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
fun RegisterScreenPreview(){
    RegisterScreen()
}