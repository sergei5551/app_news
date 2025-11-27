package ru.application.news_app.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.application.news_app.R
import ru.application.news_app.presentation.navigation.Screen
import ru.application.news_app.presentation.ui.component.CustomTextField
import ru.application.news_app.presentation.ui.component.StyledButton

@Composable
fun LoginScreen(
    onNavigationTo: (Screen) -> Unit = {}
) {

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
            Text(
                text = "Login",
                fontSize = 50.sp,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 190.dp),
            )
        }
        // Контейнер элементов
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier

        ) {
            Column(
                modifier = Modifier
                    .padding(top = 70.dp)
                    .align(Alignment.TopCenter)
            ) {
                CustomTextField(
                    value = "Email",
                    onValueChange = {},
                    modifier = Modifier
                    )
                CustomTextField(
                    value = "Password",
                    onValueChange = {},
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.bask_image_newspaper),
                contentDescription = "newspaper",
                modifier = Modifier
                    .size(400.dp)
            )
            Column() {
                StyledButton(
                    onClick = {},
                    modifier = Modifier.padding(top = 300.dp)
                ) {
                    Text(
                        text = "Sing in"
                    )
                }

                StyledButton(
                    onClick = {},
                    modifier = Modifier.padding(top = 350.dp)
                ) {
                    Text(
                        text = "Registration"
                    )
                }
            }
        }
        Text(
            text = "Forgot password?",
            color = Color(0xFF214DFF)
        )


    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview(){
    LoginScreen()
}